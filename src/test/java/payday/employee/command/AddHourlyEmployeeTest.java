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
import payday.employee.classification.HourlyClassification;
import payday.employee.method.HoldMethod;
import payday.employee.schedule.WeaklySchedule;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class AddHourlyEmployeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testAddHourlyEmployee() throws Exception {
        // given
        final Integer empId = 2;
        final String name = "Steve";
        final String address = "Home Address";
        final double hourlyWage = 100.00D;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, name, address, hourlyWage);
        // when
        t.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e, name, address);
        assertClassification(e.getClassification(HourlyClassification.class), hourlyWage);
        assertSchedule(e.getSchedule(WeaklySchedule.class));
        assertMethod(e.getMethod(HoldMethod.class));
    }

    private void assertEmployee(Employee e, String name, String address) {
        assertThat(e, is(notNullValue()));
        assertThat(e.getName(), is(name));
        assertThat(e.getAddress(), is(address));
    }

    private void assertClassification(HourlyClassification c, double hourlyWage) {
        assertThat(c, is(notNullValue()));
        assertThat(c.getRate(), is(hourlyWage));
    }

    private void assertSchedule(WeaklySchedule s) {
        assertThat(s, is(notNullValue()));
    }

    private void assertMethod(HoldMethod m) {
        assertThat(m, is(notNullValue()));
    }
}
