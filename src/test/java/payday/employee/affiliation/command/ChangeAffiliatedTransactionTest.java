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
import payday.employee.affiliation.UnionAffiliation;
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
public class ChangeAffiliatedTransactionTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testChangeMemberTransaction() throws Exception {
        // given
        final Integer empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "home", 15.25);
        t.execute();
        // when
        final double dues = 99.42;
        ChangeAffiliatedTransaction cat = new ChangeAffiliatedTransaction(empId, dues);
        cat.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e, dues);
    }

    private void assertEmployee(Employee e, double dues) {
        assertThat(e, is(notNullValue()));
        UnionAffiliation ua = e.getAffiliation(UnionAffiliation.class);
        assertThat(ua, is(notNullValue()));
        assertThat(ua.getDues(), is(dues));
    }
}
