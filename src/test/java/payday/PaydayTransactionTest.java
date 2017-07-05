package payday;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import payday.employee.affiliation.command.ChangeAffiliatedTransaction;
import payday.employee.affiliation.command.ServiceChargeTransaction;
import payday.employee.classification.command.TimeCardTransaction;
import payday.employee.command.AddHourlyEmployee;
import payday.employee.command.AddSalariedEmployee;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PaydayTransactionTest {
    @Test
    public void testPaySingleSalariedEmployee() throws Exception {
        // given
        final Integer empId = 1;
        final double salary = 1000.00;
        new AddSalariedEmployee(empId, "Bob", "Home", salary).execute();
        Date payDate = getDate(2001, Calendar.NOVEMBER, 30);
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        assertPaycheck(pc, salary, 0.0, salary, "Hold");
    }

    @SuppressWarnings("SameParameterValue")
    private void assertPaycheck(Paycheck pc, double grossPay, double deductions, double netPay, String disposition) {
        assertThat(pc, is(notNullValue()));
        assertThat(pc.getGrossPay(), is(grossPay));
        assertThat(pc.getDeductions(), is(deductions));
        assertThat(pc.getNetPay(), is(netPay));
        assertThat(pc.getField("Disposition"), is(disposition));
    }

    @SuppressWarnings("SameParameterValue")
    private Date getDate(int year, int month, int date) {
        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(year, month, date);
        return payCalendar.getTime();
    }

    @Test
    public void testPaySingleSalariedEmployeeOnWrongDate() throws Exception {
        final Integer empId = 1;
        final double salary = 1000.00;
        new AddSalariedEmployee(empId, "Bob", "Home", salary).execute();
        Date payDate = getDate(2001, Calendar.NOVEMBER, 29);
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        assertThat(pc, is(nullValue()));
    }

    @Test
    public void testPaySalariedUnionMemberDues() throws Exception {
        // given
        final Integer empId = 1;
        final double salary = 1000.00;
        new AddSalariedEmployee(empId, "Bob", "Home", salary).execute();
        final double dues = 9.42;
        new ChangeAffiliatedTransaction(empId, dues).execute();
        Date payDate = getDate(2001, Calendar.NOVEMBER, 30);
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        final double deductions = dues * 5; // 조합비 * 주 (해당 월은 금요일이 5번)
        assertPaycheck(pc, salary, deductions, salary - deductions, "Hold");
    }

    @Test
    public void testPaySingleHourlyEmployeeNoTimeCards() throws Exception {
        // given
        final Integer empId = 2;
        new AddHourlyEmployee(empId, "Bill", "Home", 15.25).execute();
        Date payDate = getDate(2017, Calendar.JULY, 7); // 금요일
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        assertPaycheck(pc, 0.0, 0.0, 0.0, "Hold");
    }

    @Test
    public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
        // given
        final Integer empId = 2;
        final double hourlyWage = 15.25;    // 시간당 급여
        new AddHourlyEmployee(empId, "Bill", "Home", hourlyWage).execute();
        Date payDate = getDate(2017, Calendar.JULY, 7); // 금요일
        double hours = 2.0;                 // 일한 시간
        new TimeCardTransaction(payDate.getTime(), hours, empId).execute();
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        final double pay = hourlyWage * hours;  // 급여 = 시간당 급여 * 일한 시간
        assertPaycheck(pc, pay, 0.0, pay, "Hold");
    }

    @Test
    public void testPaySingleHourlyEmployeeOnWrongDate() throws Exception {
        // given
        final Integer empId = 2;
        final double hourlyWage = 15.25;    // 시간당 급여
        new AddHourlyEmployee(empId, "Bill", "Home", hourlyWage).execute();
        Date payDate = getDate(2017, Calendar.JULY, 6); // 목요일
        double hours = 9.0;                 // 일한 시간
        new TimeCardTransaction(payDate.getTime(), hours, empId).execute();
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        assertThat(pc, is(nullValue()));
    }

    @Test
    public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
        // given
        final Integer empId = 2;
        final double hourlyWage = 15.25;    // 시간당 급여
        new AddHourlyEmployee(empId, "Bill", "Home", hourlyWage).execute();
        Date payDate = getDate(2017, Calendar.JULY, 7); // 금요일
        final double hours = 2.0;                 // 일한 시간1
        new TimeCardTransaction(payDate.getTime(), hours, empId).execute();
        final double hours2 = 5.0;
        new TimeCardTransaction(getDate(2017, Calendar.JULY, 6).getTime(), hours2, empId).execute();
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        final double pay = hourlyWage * (hours + hours2);  // 급여 = 시간당 급여 * 일한 시간
        assertPaycheck(pc, pay, 0.0, pay, "Hold");
    }

    @Test
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
        // given
        final Integer empId = 2;
        final double hourlyWage = 15.25;    // 시간당 급여
        new AddHourlyEmployee(empId, "Bill", "Home", hourlyWage).execute();
        Date payDate = getDate(2017, Calendar.JULY, 14); // 금요일
        final double hours = 2.0;                 // 일한 시간1
        new TimeCardTransaction(payDate.getTime(), hours, empId).execute();
        final double hours2 = 5.0;
        new TimeCardTransaction(getDate(2017, Calendar.JULY, 7).getTime(), hours2, empId).execute();    // 이전 기간에 일한 것 : 제외 대상
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        final double pay = hourlyWage * hours;  // 급여 = 시간당 급여 * 일한 시간
        assertPaycheck(pc, pay, 0.0, pay, "Hold");
    }

    @Test
    public void testPayHourlyUnionMemberServiceCharge() throws Exception {
        // given
        final Integer empId = 1;
        final double hourlyWage = 15.24;
        new AddHourlyEmployee(empId, "Bill", "Home", hourlyWage).execute();
        double dues = 9.42;
        new ChangeAffiliatedTransaction(empId, dues).execute();
        Date payDate = getDate(2001, Calendar.NOVEMBER, 9);
        final double charge = 19.42;
        new ServiceChargeTransaction(empId, payDate.getTime(), charge).execute();
        final double hours = 8.0;
        new TimeCardTransaction(payDate.getTime(), hours, empId).execute();
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        assertPaycheck(pc, hourlyWage * hours, dues + charge, (hourlyWage * hours) - (dues + charge), "Hold");
    }

    @Test
    public void testServiceChargesSpanningMultiplePayPeriods() throws Exception {
        // given
        final Integer empId = 1;
        final double hourlyWage = 15.24;
        new AddHourlyEmployee(empId, "Bill", "Home", hourlyWage).execute();
        double dues = 9.42;
        new ChangeAffiliatedTransaction(empId, dues).execute();
        Date payDate = getDate(2001, Calendar.NOVEMBER, 9);
        final double charge = 19.42;
        addServiceCharge(empId, getDate(2001, Calendar.NOVEMBER, 2), 100.00);   // 지난 주 금요일
        addServiceCharge(empId, payDate, charge);           // 기간 내
        addServiceCharge(empId, getDate(2001, Calendar.NOVEMBER, 16), 200.00);   // 다음 주 금요일
        final double hours = 8.0;
        new TimeCardTransaction(payDate.getTime(), hours, empId).execute();
        // when
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        // then
        Paycheck pc = pt.getPaycheck(empId);
        assertPaycheck(pc, hourlyWage * hours, dues + charge, (hourlyWage * hours) - (dues + charge), "Hold");
    }

    private void addServiceCharge(Integer empId, Date date, double charge) {
        new ServiceChargeTransaction(empId, date.getTime(), charge).execute();
    }
}
