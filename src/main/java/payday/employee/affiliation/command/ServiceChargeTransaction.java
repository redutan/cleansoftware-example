package payday.employee.affiliation.command;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import payday.Transaction;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;
import payday.employee.affiliation.ServiceCharge;
import payday.employee.affiliation.UnionAffiliation;

import java.util.Optional;

@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Configurable
public class ServiceChargeTransaction implements Transaction {

    private final Integer empId;
    private final long timeMillis;
    private final double charge;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ServiceChargeTransaction(@NonNull Integer empId, long timeMillis, double charge) {
        this.empId = empId;
        this.timeMillis = timeMillis;
        this.charge = charge;
    }

    @Transactional
    @Override
    public void execute() {
        Employee e = Optional.ofNullable(employeeRepository.findOne(empId))
            .orElseThrow(() -> new IllegalArgumentException("Not found employee : " + empId));
        UnionAffiliation uf = e.getAffiliation(UnionAffiliation.class);
        uf.addServiceCharge(new ServiceCharge(timeMillis, charge));
    }
}
