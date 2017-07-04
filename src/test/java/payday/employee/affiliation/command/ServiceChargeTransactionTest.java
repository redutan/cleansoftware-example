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
import payday.employee.affiliation.ServiceCharge;
import payday.employee.affiliation.UnionAffiliation;
import payday.employee.command.AddHourlyEmployee;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaydayApplication.class)
@Transactional
public class ServiceChargeTransactionTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testServiceChangeTransaction() throws Exception {
        // given
        final Integer empId = 6;
        UnionAffiliation af = fixtureEmployeeWithUnionAffiliation(empId);
        final long timeMillis = System.currentTimeMillis();
        final double amount = 12.95;
        // when
        ServiceChargeTransaction sct = new ServiceChargeTransaction(empId, timeMillis, amount);
        sct.execute();
        // then
        ServiceCharge sc = af.getServiceCharge(timeMillis);
        assertThat(sc, is(notNullValue()));
        assertThat(sc.getAmount(), is(amount));
    }

    private UnionAffiliation fixtureEmployeeWithUnionAffiliation(Integer empId) {
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();
        Employee e = employeeRepository.findOne(empId);
        UnionAffiliation af = new UnionAffiliation(12.5);
        e.setAffiliation(af);
        return af;
    }
}
