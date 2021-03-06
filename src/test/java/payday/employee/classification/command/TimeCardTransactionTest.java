package payday.employee.classification.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import payday.PaydayApplication;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;
import payday.employee.classification.HourlyClassification;
import payday.employee.classification.TimeCard;
import payday.employee.command.AddHourlyEmployee;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class TimeCardTransactionTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testTimeCardTransaction() throws Exception {
        // given
        final Integer empId = 5;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();
        final long timeMillis = System.currentTimeMillis();
        final double hours = 8.0D;
        // when
        TimeCardTransaction tct = new TimeCardTransaction(timeMillis, hours, empId);
        tct.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertThat(e, is(notNullValue()));
        assertHourlyClassification(e.getClassification(HourlyClassification.class), timeMillis, hours);
    }

    private void assertHourlyClassification(HourlyClassification hc, long timeMillis, double hours) {
        assertThat(hc, is(notNullValue()));
        TimeCard tc = hc.getTimeCard(timeMillis);
        assertThat(tc, is(notNullValue()));
        assertThat(tc.getHours(), is(hours));
    }
}
