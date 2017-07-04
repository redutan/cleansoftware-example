package payday.employee.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import payday.Transaction;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;

import java.util.Optional;

@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Configurable
public abstract class ChangeEmployeeTransaction implements Transaction{
    private final Integer empId;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ChangeEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Transactional
    @Override
    public final void execute() {
        Employee e = Optional.ofNullable(employeeRepository.findOne(empId))
            .orElseThrow(() -> new IllegalArgumentException("Not found employee : " + empId));
        change(e);
    }

    protected abstract void change(Employee e);
}
