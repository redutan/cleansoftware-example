package payday.employee.method.command;

import payday.employee.method.AbstractPaymentMethod;
import payday.employee.method.HoldMethod;

/**
 * @author myeongju.jung
 */
public class ChangeHoldTransaction extends ChangeMethodTransaction {
    ChangeHoldTransaction(Integer empId) {
        super(empId);
    }

    @Override
    AbstractPaymentMethod getMethod() {
        return new HoldMethod();
    }
}
