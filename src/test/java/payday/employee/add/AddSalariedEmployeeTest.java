package payday.employee.add;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
@SpringBootTest
public class AddSalariedEmployeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testAddSalariedEmployee() throws Exception {
        // given
        final int empId = 1;
        String name = "Bob";
        final double salary = 1000.00D;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, name, "Home", salary);
        // when
        t.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertThat(e, is(notNullValue()));
        assertThat(e.getName(), is(name));

        PaymentClassification pc = e.getClassification();
        assertThat(pc, is(notNullValue()));
        assertThat(pc, instanceOf(SalariedClassification.class));
        SalariedClassification sc = SalariedClassification.class.cast(pc);
        assertThat(sc.getSalary(), is(salary));

        PaymentSchedule ps = e.getSchedule();
        assertThat(ps, is(notNullValue()));
        assertThat(ps, instanceOf(MonthlySchedule.class));

        PaymentMethod pm = e.getMethod();
        assertThat(pm, is(notNullValue()));
        assertThat(pm, instanceOf(HoldMethod.class));
    }
}
