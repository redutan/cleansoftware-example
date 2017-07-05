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
    private final Date payDate;
    private final Map<String, String> fields = new HashMap<>();

    public Paycheck(@NonNull Date payDate) {
        this.payDate = (Date) payDate.clone();
    }

    public String getField(@NonNull String key) {
        return fields.get(key);
    }

    public void setField(@NonNull String key, String value) {
        fields.put(key, value);
    }

    public Date getPayDate() {
        return (Date) payDate.clone();
    }

    public void details(double grossPay, double deductions) {
        this.grossPay = grossPay;
        this.deductions = deductions;
        this.netPay = grossPay - deductions;
    }
}
