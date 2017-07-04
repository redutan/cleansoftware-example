package payday.employee.classification.command;


import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import payday.Transaction;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;
import payday.employee.classification.CommissionedClassification;
import payday.employee.classification.SalesReceipt;

import java.util.Optional;

@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Configurable
public class SalesReceiptTransaction implements Transaction {
    private final long timeMillis;
    private final double amount;
    private final Integer empId;
    @Autowired
    private EmployeeRepository employeeRepository;

    public SalesReceiptTransaction(long timeMillis, double amount, @NotNull Integer empId) {
        this.timeMillis = timeMillis;
        this.amount = amount;
        this.empId = empId;
    }

    @Override
    public void execute() {
        Employee e = Optional.ofNullable(employeeRepository.findOne(empId))
            .orElseThrow(() -> new IllegalArgumentException("Not found employee : " + empId));
        try {
            CommissionedClassification cc = e.getClassification(CommissionedClassification.class);
            cc.addSalesReceipt(new SalesReceipt(timeMillis, amount));
        } catch (ClassCastException cce) {
            throw new IllegalStateException("Tried to add timecard to non-commissioned employee :  " + empId);
        }
    }
}
