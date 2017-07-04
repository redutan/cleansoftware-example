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
import payday.employee.command.AddCommissionedEmployee;
import payday.employee.schedule.WeaklySchedule;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class ChangeHourlyTransactionTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testChangeHourlyTransaction() throws Exception {
        // given
        final Integer empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();
        final double rate = 27.52;
        // when
        ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, rate);
        cht.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e, rate);
    }

    private void assertEmployee(Employee e, double rate) {
        assertThat(e, is(notNullValue()));
        HourlyClassification hc = e.getClassification(HourlyClassification.class);
        assertThat(hc, is(notNullValue()));
        assertThat(hc.getRate(), is(rate));
        WeaklySchedule ws = e.getSchedule(WeaklySchedule.class);
        assertThat(ws, is(notNullValue()));
    }
}
