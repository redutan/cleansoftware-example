package payday.employee.method.command;

import payday.employee.Employee;
import payday.employee.command.ChangeEmployeeTransaction;
import payday.employee.method.AbstractPaymentMethod;

/**
 * @author myeongju.jung
 */
abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
    ChangeMethodTransaction(Integer empId) {
        super(empId);
    }

    @Override
    protected final void change(Employee e) {
        e.setMethod(getMethod());
    }

    abstract AbstractPaymentMethod getMethod();
}
