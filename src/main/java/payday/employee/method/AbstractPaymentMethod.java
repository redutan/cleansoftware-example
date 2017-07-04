package payday.employee.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import payday.employee.PaymentMethod;

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
@EqualsAndHashCode
public class AbstractPaymentMethod implements PaymentMethod {
    @Id
    private Integer methodId;
}
