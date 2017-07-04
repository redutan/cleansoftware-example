package payday.employee.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import payday.PaydayApplication;
import payday.employee.*;
import payday.employee.classification.SalariedClassification;
import payday.employee.method.HoldMethod;
import payday.employee.schedule.MonthlySchedule;

import static org.hamcrest.Matchers.*;
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
        assertClassification(e.getClassification(), salary);
        assertSchedule(e.getSchedule());
        assertMethod(e.getMethod());
    }

    private void assertEmployee(Employee e, String name, String address) {
        assertThat(e, is(notNullValue()));
        assertThat(e.getName(), is(name));
        assertThat(e.getAddress(), is(address));
    }

    private void assertClassification(PaymentClassification pc, double salary) {
        assertThat(pc, is(notNullValue()));
        assertThat(pc, instanceOf(SalariedClassification.class));
        SalariedClassification sc = SalariedClassification.class.cast(pc);
        assertThat(sc.getSalary(), is(salary));
    }

    private void assertSchedule(PaymentSchedule ps) {
        assertThat(ps, is(notNullValue()));
        assertThat(ps, instanceOf(MonthlySchedule.class));
    }

    private void assertMethod(PaymentMethod pm) {
        assertThat(pm, is(notNullValue()));
        assertThat(pm, instanceOf(HoldMethod.class));
    }
}
