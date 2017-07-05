package payday.employee.affiliation;

import lombok.*;
import payday.Paycheck;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@DiscriminatorValue("U")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnionAffiliation extends AbstractAffiliation {
    private double dues;
    @OneToMany
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
        // TODO
        return 0;
    }
}
