package payday.employee.classification.command;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import payday.Transaction;
import payday.employee.EmployeeRepository;

/**
 * @author myeongju.jung
 */
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

    @Override
    public void execute() {
        // TODO
    }
}
