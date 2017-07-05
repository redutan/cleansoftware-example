package payday.employee.method;

import lombok.*;
import payday.Paycheck;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author myeongju.jung
 */
@Entity
@DiscriminatorValue("M")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MailMethod extends AbstractPaymentMethod {
    @NonNull
    private String address;

    @Override
    public void pay(Paycheck pc) {
        pc.setField("Disposition", "Mail");
    }
}
