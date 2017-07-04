package payday.employee.add;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import payday.PaydayApplication;
import payday.employee.*;
import payday.employee.classification.CommissionedClassification;
import payday.employee.method.HoldMethod;
import payday.employee.schedule.BiWeaklySchedule;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
public class AddCommissionedEmployeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testAddCommissionedEmployee() throws Exception {
        // given
        final Integer empId = 3;
        final String name = "Robert";
        final String address = "Office Address";
        final double salary = 10000.00D;
        final double commissionRate = 0.1D;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, name, address, salary, commissionRate);
        // when
        t.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e, name, address);
        assertClassification(e.getClassification(), salary, commissionRate);
        assertSchedule(e.getSchedule());
        assertMethod(e.getMethod());
    }

    private void assertEmployee(Employee e, String name, String address) {
        assertThat(e, is(notNullValue()));
        assertThat(e.getName(), is(name));
        assertThat(e.getAddress(), is(address));
    }

    private void assertClassification(PaymentClassification pc, double salary, double commissionRate) {
        assertThat(pc, is(notNullValue()));
        assertThat(pc, instanceOf(CommissionedClassification.class));
        CommissionedClassification cc = CommissionedClassification.class.cast(pc);
        assertThat(cc.getSalary(), is(salary));
        assertThat(cc.getCommissionRate(), is(commissionRate));
    }

    private void assertSchedule(PaymentSchedule ps) {
        assertThat(ps, is(notNullValue()));
        assertThat(ps, instanceOf(BiWeaklySchedule.class));
    }

    private void assertMethod(PaymentMethod pm) {
        assertThat(pm, is(notNullValue()));
        assertThat(pm, instanceOf(HoldMethod.class));
    }
}
