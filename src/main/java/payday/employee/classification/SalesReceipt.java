package payday.employee.classification;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SalesReceipt {
    @Id
    private Long timeMillis;
    private double amount;

    public boolean equalsTimeMillis(long timeMillis) {
        return this.timeMillis != null && this.timeMillis.equals(timeMillis);
    }
}
