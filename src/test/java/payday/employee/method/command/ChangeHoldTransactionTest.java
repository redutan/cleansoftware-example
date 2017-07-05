package payday.employee.method.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import payday.PaydayApplication;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;
import payday.employee.command.AddSalariedEmployee;
import payday.employee.method.HoldMethod;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class ChangeHoldTransactionTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testChangeHoldTransaction() throws Exception {
        // given
        final Integer empId = 9;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 5000.00);
        t.execute();
        // when
        ChangeHoldTransaction cmt = new ChangeHoldTransaction(empId);
        cmt.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e);
    }

    private void assertEmployee(Employee e) {
        assertThat(e, is(notNullValue()));
        HoldMethod hm = e.getMethod(HoldMethod.class);
        assertThat(hm, is(notNullValue()));
    }
}
