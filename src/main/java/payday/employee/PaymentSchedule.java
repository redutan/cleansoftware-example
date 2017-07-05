package payday.employee;

import java.util.Date;

/**
 * @author myeongju.jung
 */
public interface PaymentSchedule {
    boolean isPayDate(Date payDate);
}
