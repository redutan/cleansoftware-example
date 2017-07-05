package payday.employee.affiliation.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import payday.PaydayApplication;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;
import payday.employee.affiliation.AbstractAffiliation;
import payday.employee.command.AddHourlyEmployee;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class ChangeUnaffiliatedTransactionTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testChangeUnaffiliatedTransaction() throws Exception {
        // given
        final Integer empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "home", 15.25);
        t.execute();
        // when
        ChangeUnaffiliatedTransaction cut = new ChangeUnaffiliatedTransaction(empId);
        cut.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e);
    }

    private void assertEmployee(Employee e) {
        assertThat(e, is(notNullValue()));
        assertThat(e.getAffiliation(), is(AbstractAffiliation.NONE));
    }
}
