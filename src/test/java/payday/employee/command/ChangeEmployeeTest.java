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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class ChangeEmployeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testChangeName() throws Exception {
        // given
        final Integer empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();
        final String newName = "Bob";
        // when
        ChangeNameTransaction cnt = new ChangeNameTransaction(empId, newName);
        cnt.execute();
        // then
        Employee modifed = employeeRepository.findOne(empId);
        assertThat(modifed, is(notNullValue()));
        assertThat(modifed.getName(), is(newName));
    }
}
