package payday.employee.command;

import org.springframework.beans.factory.annotation.Configurable;
import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.classification.CommissionedClassification;
import payday.employee.schedule.AbstractPaymentSchedule;
import payday.employee.schedule.BiWeaklySchedule;

/**
 * @author myeongju.jung
 */
@Configurable
public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private final double salary;
    private final double commissionRate;

    public AddCommissionedEmployee(Integer empId, String name, String address, double salary, double commissionRate) {
        super(empId, name, address);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    AbstractPaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    AbstractPaymentSchedule getSchedule() {
        return new BiWeaklySchedule();
    }
}
