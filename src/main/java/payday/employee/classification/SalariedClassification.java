package payday.employee.classification;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author myeongju.jung
 */
@Entity
@DiscriminatorValue("S")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SalariedClassification extends AbstractPaymentClassification {
    private double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }
}
