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
import payday.employee.classification.CommissionedClassification;
import payday.employee.method.HoldMethod;
import payday.employee.schedule.BiWeaklySchedule;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
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
        assertClassification(e.getClassification(CommissionedClassification.class), salary, commissionRate);
        assertSchedule(e.getSchedule(BiWeaklySchedule.class));
        assertMethod(e.getMethod(HoldMethod.class));
    }

    private void assertEmployee(Employee e, String name, String address) {
        assertThat(e, is(notNullValue()));
        assertThat(e.getName(), is(name));
        assertThat(e.getAddress(), is(address));
    }

    private void assertClassification(CommissionedClassification c, double salary, double commissionRate) {
        assertThat(c, is(notNullValue()));
        assertThat(c.getSalary(), is(salary));
        assertThat(c.getCommissionRate(), is(commissionRate));
    }

    private void assertSchedule(BiWeaklySchedule s) {
        assertThat(s, is(notNullValue()));
    }

    private void assertMethod(HoldMethod m) {
        assertThat(m, is(notNullValue()));
    }
}
