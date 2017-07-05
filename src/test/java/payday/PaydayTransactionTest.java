package payday;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import payday.employee.command.AddSalariedEmployee;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
        assertThat(pc, is(notNullValue()));
        assertThat(pc.getGrossPay(), is(salary));
        assertThat(pc.getDeductions(), is(0.0D));
        assertThat(pc.getNetPay(), is(1000.00D));
        assertThat(pc.getField("Disposition"), is("Hold"));
    }

    @SuppressWarnings("SameParameterValue")
    private Date getDate(int year, int month, int date) {
        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(year, month, date);
        return payCalendar.getTime();
    }
}
