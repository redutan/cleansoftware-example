package payday.employee.classification;

import lombok.*;
import payday.Paycheck;

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
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HourlyClassification extends AbstractPaymentClassification {
    // 이 시간이 넘어가면 시급 overtime 가산
    private static final double OVERTIME_LIMIT_HOURS = 8.0;
    // overtime 시급 가산 비율
    private static final double OVERTIME_RATE = 1.5;
    @Getter
    private double rate;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<TimeCard> timeCards = new ArrayList<>();

    public HourlyClassification(double rate) {
        super();
        this.rate = rate;
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

    @Override
    public double calculatePay(Paycheck pc) {
        double totalPay = 0.0;
        for (TimeCard timeCard : getTimeCards()) {
            if (!isInPayPeriod(timeCard.getDate(), pc)) {
                continue;
            }
            totalPay += calculatePayForTimeCard(timeCard);
        }
        return totalPay;
    }

    private double calculatePayForTimeCard(TimeCard timeCard) {
        final double hours = timeCard.getHours();
        final double overtime = Math.max(0.0, hours - OVERTIME_LIMIT_HOURS);
        final double straightTime = hours - overtime;
        return (straightTime * rate) + (overtime * rate * OVERTIME_RATE);
    }
}
