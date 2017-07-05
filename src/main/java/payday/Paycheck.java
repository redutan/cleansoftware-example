package payday;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myeongju.jung
 */
@ToString
@EqualsAndHashCode
public class Paycheck {
    @Getter
    private double grossPay;
    @Getter
    private double deductions;
    @Getter
    private double netPay;
    private final Date payPeriodStartDate;
    private final Date payPeriodEndDate;
    private final Map<String, String> fields = new HashMap<>();

    public Paycheck(@NonNull Date payPeriodStartDate, @NonNull Date payPeriodEndDate) {
        this.payPeriodStartDate = (Date) payPeriodStartDate.clone();
        this.payPeriodEndDate = (Date) payPeriodEndDate.clone();
    }

    @SuppressWarnings("SameParameterValue")
    public String getField(@NonNull String key) {
        return fields.get(key);
    }

    @SuppressWarnings("SameParameterValue")
    public void setField(@NonNull String key, String value) {
        fields.put(key, value);
    }

    public Date getPayPeriodStartDate() {
        return (Date) payPeriodStartDate.clone();
    }

    public Date getPayPeriodEndDate() {
        return (Date) payPeriodEndDate.clone();
    }

    public void details(double grossPay, double deductions) {
        this.grossPay = grossPay;
        this.deductions = deductions;
        this.netPay = grossPay - deductions;
    }
}
