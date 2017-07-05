package payday.employee.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import payday.employee.PaymentMethod;

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
public abstract class AbstractPaymentMethod implements PaymentMethod {
    @Id
    @GeneratedValue
    private Integer methodId;
}
