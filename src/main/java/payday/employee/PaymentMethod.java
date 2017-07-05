package payday.employee;

import payday.Paycheck;

/**
 * @author myeongju.jung
 */
public interface PaymentMethod {
    void pay(Paycheck pc);
}
