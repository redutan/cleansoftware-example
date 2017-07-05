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
import payday.employee.method.DirectMethod;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class ChangeDirectTransactionTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testChangeDirectionTransaction() throws Exception {
        // given
        final Integer empId = 7;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 5000.00);
        t.execute();
        final String bank = "BANK";
        final String account = "111-111111-11";
        // when
        ChangeDirectTransaction cdt = new ChangeDirectTransaction(empId, bank, account);
        cdt.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e, bank, account);
    }

    private void assertEmployee(Employee e, String bank, String account) {
        assertThat(e, is(notNullValue()));
        DirectMethod dm = e.getMethod(DirectMethod.class);
        assertThat(dm, is(notNullValue()));
        assertThat(dm.getBank(), is(bank));
        assertThat(dm.getAccount(), is(account));
    }
}
