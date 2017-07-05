package payday.employee.classification;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import payday.Paycheck;
import payday.employee.PaymentClassification;
import payday.utils.DateUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @author myeongju.jung
 */
@Entity
@Inheritance
@DiscriminatorColumn
@Getter
@ToString
@EqualsAndHashCode
public abstract class AbstractPaymentClassification implements PaymentClassification {
    @Id
    @GeneratedValue
    private Integer classificationId;

    boolean isInPayPeriod(@NonNull Date payDate, @NonNull Paycheck pc) {
        return DateUtils.between(payDate, pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
    }


}
