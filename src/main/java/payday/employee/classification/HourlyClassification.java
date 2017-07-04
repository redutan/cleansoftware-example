package payday.employee.classification;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<TimeCard> timeCards = new ArrayList<>();

    public HourlyClassification(double hourlyWage) {
        super();
        this.hourlyWage = hourlyWage;
    }

    public TimeCard getTimeCard(long timeMillis) {
        if (timeCards == null) {
            return null;
        }
        return getTimeCards().stream()
            .filter(tc -> tc.equalsTimeMillis(timeMillis))
            .findAny()
            .orElse(null);
    }

    public void addTimeCard(@NonNull TimeCard timeCard) {
        getTimeCards().add(timeCard);
    }
}
