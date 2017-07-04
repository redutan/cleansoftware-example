package payday.employee.add;

import org.springframework.beans.factory.annotation.Configurable;
import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.classification.SalariedClassification;
import payday.employee.schedule.AbstractPaymentSchedule;
import payday.employee.schedule.MonthlySchedule;

/**
 * @author myeongju.jung
 */
@Configurable(dependencyCheck = true)
public class AddSalariedEmployee extends AddEmployeeTransaction {
    private double salary;

    public AddSalariedEmployee() {
        super();
    }

    public AddSalariedEmployee(Integer empId, String name, String address, double salary) {
        super(empId, name, address);
        this.salary = salary;
    }

    public void set(Integer empId, String name, String address, double salary) {
        super.set(empId, name, address);
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
