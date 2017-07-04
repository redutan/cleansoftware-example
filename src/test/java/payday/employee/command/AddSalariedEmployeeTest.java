package payday.employee.command;

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
import payday.employee.method.HoldMethod;
import payday.employee.schedule.MonthlySchedule;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class AddSalariedEmployeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testAddSalariedEmployee() throws Exception {
        // given
        final Integer empId = 1;
        final String name = "Bob";
        final String address = "Home";
        final double salary = 10000.00D;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, name, address, salary);
        // when
        t.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e, name, address);
        assertClassification(e.getClassification(SalariedClassification.class), salary);
        assertSchedule(e.getSchedule(MonthlySchedule.class));
        assertMethod(e.getMethod(HoldMethod.class));
    }

    private void assertEmployee(Employee e, String name, String address) {
        assertThat(e, is(notNullValue()));
        assertThat(e.getName(), is(name));
        assertThat(e.getAddress(), is(address));
    }

    private void assertClassification(SalariedClassification c, double salary) {
        assertThat(c, is(notNullValue()));
        assertThat(c.getSalary(), is(salary));
    }

    private void assertSchedule(MonthlySchedule s) {
        assertThat(s, is(notNullValue()));
    }

    private void assertMethod(HoldMethod m) {
        assertThat(m, is(notNullValue()));
    }
}
