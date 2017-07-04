package payday.employee.schedule;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import payday.employee.PaymentSchedule;

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
public class AbstractPaymentSchedule implements PaymentSchedule {
    @Id
    private Integer scheduleId;
}
