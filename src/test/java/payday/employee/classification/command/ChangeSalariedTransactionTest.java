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
import payday.employee.classification.SalariedClassification;
import payday.employee.command.AddHourlyEmployee;
import payday.employee.schedule.MonthlySchedule;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class ChangeSalariedTransactionTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testChangeSalariedTransaction() throws Exception {
        // given
        final Integer empId = 4;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bob", "Home", 25.24);
        t.execute();
        final double salary = 2500.00;
        // when
        ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId, salary);
        cst.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e, salary);
    }

    private void assertEmployee(Employee e, double salary) {
        assertThat(e, is(notNullValue()));
        SalariedClassification sc = e.getClassification(SalariedClassification.class);
        assertThat(sc, is(notNullValue()));
        assertThat(sc.getSalary(), is(salary));
        MonthlySchedule ms = e.getSchedule(MonthlySchedule.class);
        assertThat(ms, is(notNullValue()));
    }
}
