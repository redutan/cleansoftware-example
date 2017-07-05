package payday.employee.schedule;

import lombok.*;
import payday.utils.DateUtils;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Date;

/**
 * @author myeongju.jung
 */
@Entity
@DiscriminatorValue("M")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MonthlySchedule extends AbstractPaymentSchedule {
    @Override
    public boolean isPayDate(@NonNull Date payDate) {
        return isLastDayOfMonth(payDate);
    }

    private boolean isLastDayOfMonth(Date payDate) {
        Calendar payCalendar = Calendar.getInstance();
        payCalendar.setTime(payDate);
        int month = payCalendar.get(Calendar.MONTH);
        payCalendar.add(Calendar.DATE, 1);
        int monthByAdded1Day = payCalendar.get(Calendar.MONTH);
        return month != monthByAdded1Day;
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        Calendar result = DateUtils.toCalendar(payPeriodEndDate);
        result.set(Calendar.DAY_OF_MONTH, 1);
        return result.getTime();
    }
}
