package payday.employee.add;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import payday.PaydayApplication;
import payday.employee.*;
import payday.employee.classification.HourlyClassification;
import payday.employee.method.HoldMethod;
import payday.employee.schedule.WeaklySchedule;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
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
        assertClassification(e.getClassification(), hourlyWage);
        assertSchedule(e.getSchedule());
        assertMethod(e.getMethod());
    }

    private void assertEmployee(Employee e, String name, String address) {
        assertThat(e, is(notNullValue()));
        assertThat(e.getName(), is(name));
        assertThat(e.getAddress(), is(address));
    }

    private void assertClassification(PaymentClassification pc, double hourlyWage) {
        assertThat(pc, is(notNullValue()));
        assertThat(pc, instanceOf(HourlyClassification.class));
        HourlyClassification sc = HourlyClassification.class.cast(pc);
        assertThat(sc.getHourlyWage(), is(hourlyWage));
    }

    private void assertSchedule(PaymentSchedule ps) {
        assertThat(ps, is(notNullValue()));
        assertThat(ps, instanceOf(WeaklySchedule.class));
    }

    private void assertMethod(PaymentMethod pm) {
        assertThat(pm, is(notNullValue()));
        assertThat(pm, instanceOf(HoldMethod.class));
    }
}
