package payday.employee.schedule;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Date;

/**
 * @author myeongju.jung
 */
@Entity
@DiscriminatorValue("W")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WeaklySchedule extends AbstractPaymentSchedule {
    @Override
    public boolean isPayDate(Date payDate) {
        return isFriday(payDate);
    }

    private boolean isFriday(Date payDate) {
        Calendar payCalendar = Calendar.getInstance();
        payCalendar.setTime(payDate);
        int dayOfWeek = payCalendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.FRIDAY;
    }
}
