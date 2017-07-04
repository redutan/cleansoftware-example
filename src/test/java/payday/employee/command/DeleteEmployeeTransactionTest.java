package payday.employee.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import payday.PaydayApplication;
import payday.employee.EmployeeRepository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class DeleteEmployeeTransactionTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testDeleteEmployee() throws Exception {
        // given
        final Integer empId = 4;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();
        // when
        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();
        // then
        assertThat(employeeRepository.findOne(empId), is(nullValue()));
    }
}
