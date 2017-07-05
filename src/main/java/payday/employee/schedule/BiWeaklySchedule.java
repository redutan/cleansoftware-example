package payday.employee.schedule;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author myeongju.jung
 */
@Entity
@DiscriminatorValue("BW")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BiWeaklySchedule extends AbstractPaymentSchedule {
    @Override
    public boolean isPayDate(Date payDate) {
        return false;
    }
}
