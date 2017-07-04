package payday.employee.command;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import payday.Transaction;
import payday.employee.EmployeeRepository;

/**
 * @author myeongju.jung
 */
@Configurable
public class DeleteEmployeeTransaction implements Transaction {
    private final Integer empId;

    @Autowired
    private EmployeeRepository employeeRepository;

    public DeleteEmployeeTransaction(@NonNull Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        employeeRepository.delete(empId);
    }
}
