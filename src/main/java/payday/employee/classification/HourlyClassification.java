package payday.employee.classification;

import lombok.*;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HourlyClassification extends AbstractPaymentClassification {
    private double hourlyWage;

    public HourlyClassification(double hourlyWage) {
        super();
        this.hourlyWage = hourlyWage;
    }
}
