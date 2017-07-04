package payday.employee.classification;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import payday.employee.PaymentClassification;

import javax.persistence.*;

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
}
