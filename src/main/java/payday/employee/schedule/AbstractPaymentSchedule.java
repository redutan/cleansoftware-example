package payday.employee.schedule;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import payday.employee.PaymentSchedule;

import javax.persistence.*;
import java.util.Date;

/**
 * @author myeongju.jung
 */
@Entity
@Inheritance
@DiscriminatorColumn
@Getter
@ToString
@EqualsAndHashCode
public abstract class AbstractPaymentSchedule implements PaymentSchedule {
    @Id
    @GeneratedValue
    private Integer scheduleId;

    public abstract Date getPayPeriodStartDate(Date payPeriodEndDate);
}
