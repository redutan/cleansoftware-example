package payday.employee.method.command;

import payday.employee.method.AbstractPaymentMethod;
import payday.employee.method.MailMethod;

/**
 * @author myeongju.jung
 */
public class ChangeMailTransaction extends ChangeMethodTransaction {
    private final String address;

    public ChangeMailTransaction(Integer empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    AbstractPaymentMethod getMethod() {
        return new MailMethod(address);
    }
}
