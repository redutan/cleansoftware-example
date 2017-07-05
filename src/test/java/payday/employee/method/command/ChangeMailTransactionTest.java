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
import payday.employee.method.MailMethod;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class ChangeMailTransactionTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testChangeMailTransaction() throws Exception {
        // given
        final Integer empId = 7;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 5000.00);
        t.execute();
        final String address = "address@domain.com";
        // when
        ChangeMailTransaction cmt = new ChangeMailTransaction(empId, address);
        cmt.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e, address);
    }

    private void assertEmployee(Employee e, String address) {
        assertThat(e, is(notNullValue()));
        MailMethod dm = e.getMethod(MailMethod.class);
        assertThat(dm, is(notNullValue()));
        assertThat(dm.getAddress(), is(address));
    }
}
