package payday.employee.classification.command;

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
import payday.employee.command.AddHourlyEmployee;
import payday.employee.schedule.BiWeaklySchedule;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class ChangeCommissionedTransactionTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testCnahgeCommissionedTransaction() throws Exception {
        // given
        final Integer empId = 4;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Lance", "Home", 25.32);
        t.execute();
        final double salary = 3500.00;
        final double commissionRate = 2.2;
        // when
        ChangeCommissionedTransaction cct = new ChangeCommissionedTransaction(empId, salary, commissionRate);
        cct.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertEmployee(e, salary, commissionRate);
    }

    private void assertEmployee(Employee e, double salary, double commissionRate) {
        assertThat(e, is(notNullValue()));
        CommissionedClassification cc = e.getClassification(CommissionedClassification.class);
        assertThat(cc, is(notNullValue()));
        assertThat(cc.getSalary(), is(salary));
        assertThat(cc.getCommissionRate(), is(commissionRate));
        BiWeaklySchedule bws = e.getSchedule(BiWeaklySchedule.class);
        assertThat(bws, is(notNullValue()));
    }
}
