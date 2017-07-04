package payday.employee.command;

import org.springframework.beans.factory.annotation.Configurable;
import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.classification.HourlyClassification;
import payday.employee.schedule.AbstractPaymentSchedule;
import payday.employee.schedule.WeaklySchedule;

/**
 * @author myeongju.jung
 */
@Configurable
public class AddHourlyEmployee extends AddEmployeeTransaction {
    private final double hourlyWage;

    public AddHourlyEmployee(Integer empId, String name, String address, double hourlyWage) {
        super(empId, name, address);
        this.hourlyWage = hourlyWage;
    }

    @Override
    AbstractPaymentSchedule getSchedule() {
        return new WeaklySchedule();
    }

    @Override
    AbstractPaymentClassification getClassification() {
        return new HourlyClassification(this.hourlyWage);
    }
}
