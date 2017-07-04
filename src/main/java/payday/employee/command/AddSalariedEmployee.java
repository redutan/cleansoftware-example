package payday.employee.command;

import org.springframework.beans.factory.annotation.Configurable;
import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.classification.SalariedClassification;
import payday.employee.schedule.AbstractPaymentSchedule;
import payday.employee.schedule.MonthlySchedule;

/**
 * @author myeongju.jung
 */
@Configurable
public class AddSalariedEmployee extends AddEmployeeTransaction {
    private final double salary;

    public AddSalariedEmployee(Integer empId, String name, String address, double salary) {
        super(empId, name, address);
        this.salary = salary;
    }

    @Override
    AbstractPaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    AbstractPaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
