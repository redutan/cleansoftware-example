package payday.employee.classification.command;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import payday.Transaction;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;
import payday.employee.classification.HourlyClassification;
import payday.employee.classification.TimeCard;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Configurable
public class TimeCardTransaction implements Transaction {
    private final Long timeMillis;
    private final double hours;
    private final Integer empId;
    @Autowired
    private EmployeeRepository employeeRepository;

    public TimeCardTransaction(@NonNull Long timeMillis, double hours, @NonNull Integer empId) {
        this.timeMillis = timeMillis;
        this.hours = hours;
        this.empId = empId;
    }

    @Transactional
    @Override
    public void execute() {
        Employee e = employeeRepository.findOne(empId);
        if (e == null) {
            throw new IllegalStateException("Not found employee : " + empId);
        }
        try {
            HourlyClassification hc = e.getClassification(HourlyClassification.class);
            hc.addTimeCard(new TimeCard(timeMillis, hours));
        } catch (ClassCastException cce) {
            throw new IllegalStateException("Tried to add timecard to non-hourly employee :  " + empId);
        }
    }
}
