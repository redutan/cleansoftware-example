package payday.employee.classification;

import lombok.Getter;
import lombok.ToString;
import payday.employee.PaymentClassification;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author myeongju.jung
 */
@Entity
@DiscriminatorColumn
@Getter
@ToString
public abstract class AbstractPaymentClassification implements PaymentClassification {
    @Id
    private Integer classificationId;
}
