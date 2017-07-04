package payday.employee.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author myeongju.jung
 */
@Entity
@DiscriminatorValue("H")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class HoldMethod extends AbstractPaymentMethod {
}
