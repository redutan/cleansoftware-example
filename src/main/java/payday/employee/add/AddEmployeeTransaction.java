package payday.employee.add;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import payday.Transaction;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;
import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.method.AbstractPaymentMethod;
import payday.employee.method.HoldMethod;
import payday.employee.schedule.AbstractPaymentSchedule;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public abstract class AddEmployeeTransaction implements Transaction {
    private final Integer empId;
    private final String name;
    private final String address;

    @Autowired
    private EmployeeRepository employeeRepository;

    AddEmployeeTransaction(@NonNull Integer empId, @NonNull String name, @NonNull String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    @Transactional
    @Override
    public final void execute() {
        AbstractPaymentClassification pc = getClassification();
        AbstractPaymentSchedule ps = getSchedule();
        AbstractPaymentMethod pm = new HoldMethod();
        Employee e = new Employee(empId, name, address, pc, ps, pm);
        employeeRepository.save(e);
    }

    abstract AbstractPaymentSchedule getSchedule();

    abstract AbstractPaymentClassification getClassification();
}
