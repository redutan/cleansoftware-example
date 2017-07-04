package payday.employee.affiliation;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ServiceCharge {
    @Id
    private Long timeMillis;
    private double amount;

    public boolean equalsTimeMillis(Long timeMillis) {
        return this.timeMillis != null && this.timeMillis.equals(timeMillis);
    }
}
