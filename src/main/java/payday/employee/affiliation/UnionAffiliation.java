package payday.employee.affiliation;

import lombok.*;
import payday.Paycheck;
import payday.utils.DateUtils;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


@Entity
@DiscriminatorValue("U")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnionAffiliation extends AbstractAffiliation {
    private double dues;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ServiceCharge> serviceCharges = new ArrayList<>();

    public UnionAffiliation(double dues) {
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(long timeMillis) {
        return getServiceCharges().stream()
            .filter(sc -> sc.equalsTimeMillis(timeMillis))
            .findAny()
            .orElse(null);
    }

    public void addServiceCharge(@NonNull ServiceCharge serviceCharge) {
        getServiceCharges().add(serviceCharge);
    }

    @Override
    public double calculateDeductions(Paycheck pc) {
        int fridayCount = numberOfFridayCountInPeriod(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
        return dues * fridayCount + getTotalServiceCharge(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
    }

    private double getTotalServiceCharge(Date payPeriodStartDate, Date payPeriodEndDate) {
        return getServiceCharges().stream()
            .filter(sc -> DateUtils.between(new Date(sc.getTimeMillis()), payPeriodStartDate, payPeriodEndDate))
            .mapToDouble(ServiceCharge::getAmount)
            .sum();
    }

    private int numberOfFridayCountInPeriod(Date startDate, Date endDate) {
        int count = 0;
        Calendar startCalendar = DateUtils.toCalendar(startDate);
        Calendar endCalendar = DateUtils.toCalendar(endDate);
        while (startCalendar.compareTo(endCalendar) <= 0) {
            if (startCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                count++;
            }
            startCalendar.add(Calendar.DATE, 1);
        }
        return count;
    }
}
