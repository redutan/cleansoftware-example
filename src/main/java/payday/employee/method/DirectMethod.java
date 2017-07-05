package payday.employee.method;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author myeongju.jung
 */
@Entity
@DiscriminatorValue("D")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DirectMethod extends AbstractPaymentMethod {
    private String bank;
    private String account;

    public DirectMethod(@NonNull String bank, @NonNull String account) {
        super();
        this.bank = bank;
        this.account = account;
    }
}
