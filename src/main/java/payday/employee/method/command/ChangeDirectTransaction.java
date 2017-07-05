package payday.employee.method.command;

import lombok.NonNull;
import payday.employee.method.AbstractPaymentMethod;
import payday.employee.method.DirectMethod;

/**
 * @author myeongju.jung
 */
public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private final String bank;
    private final String account;

    public ChangeDirectTransaction(@NonNull Integer empId, @NonNull String bank, @NonNull String account) {
        super(empId);
        this.bank = bank;
        this.account = account;
    }

    @Override
    AbstractPaymentMethod getMethod() {
        return new DirectMethod(bank, account);
    }
}
