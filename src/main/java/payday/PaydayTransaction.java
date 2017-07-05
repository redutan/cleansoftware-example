package payday;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import payday.employee.Employee;
import payday.employee.EmployeeRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Configurable
public class PaydayTransaction implements Transaction {
    private final Date payDate;
    // 직원 별 지급수표
    private final Map<Integer, Paycheck> paychecks = new HashMap<>();
    @Autowired
    private EmployeeRepository employeeRepository;

    public PaydayTransaction(Date payDate) {
        this.payDate = (Date) payDate.clone();
    }

    @Override
    public void execute() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee e : employees) {
            if (!e.isPayDate(payDate)) {
                continue;
            }
            Paycheck pc = e.payday(payDate);
            paychecks.put(e.getEmpId(), pc);
        }
    }

    public Paycheck getPaycheck(@NonNull Integer empId) {
        return paychecks.get(empId);
    }
}
