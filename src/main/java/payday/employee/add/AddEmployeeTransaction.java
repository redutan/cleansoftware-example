package payday.employee.add;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
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
@Configurable(dependencyCheck = true)
public abstract class AddEmployeeTransaction implements Transaction {
    private Integer empId;
    private String name;
    private String address;

    @Autowired
    private EmployeeRepository employeeRepository;

    AddEmployeeTransaction() {
    }

    AddEmployeeTransaction(@NonNull Integer empId, @NonNull String name, @NonNull String address) {
        set(empId, name, address);
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

    void set(@NonNull Integer empId, @NonNull String name, @NonNull String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }
}
