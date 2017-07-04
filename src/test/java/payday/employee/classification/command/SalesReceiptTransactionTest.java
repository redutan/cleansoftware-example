package payday.employee.classification.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;
import payday.employee.classification.CommissionedClassification;
import payday.employee.classification.SalesReceipt;
import payday.employee.command.AddCommissionedEmployee;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SalesReceiptTransactionTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSalesReceiptTransaction() throws Exception {
        // given
        final Integer empId = 5;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill", "Home", 2500, 1.2);
        t.execute();
        final long timeMillis = System.currentTimeMillis();
        final double amount = 100.0D;
        // when
        SalesReceiptTransaction srt = new SalesReceiptTransaction(timeMillis, amount, empId);
        srt.execute();
        // then
        Employee e = employeeRepository.findOne(empId);
        assertThat(e, is(notNullValue()));
        assertCommissionedClassification(e.getClassification(CommissionedClassification.class), timeMillis, amount);
    }

    private void assertCommissionedClassification(CommissionedClassification cc, long timeMillis, double amount) {
        assertThat(cc, is(notNullValue()));
        SalesReceipt sr = cc.getSalesReceipt(timeMillis);
        assertThat(sr, is(notNullValue()));
        assertThat(sr.getAmount(), is(amount));
    }
}
