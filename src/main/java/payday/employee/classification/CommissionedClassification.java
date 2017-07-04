package payday.employee.classification;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author myeongju.jung
 */
@Entity
@DiscriminatorValue("C")
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommissionedClassification extends AbstractPaymentClassification {
    private double salary;
    private double commissionRate;
    @Getter(AccessLevel.PACKAGE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<SalesReceipt> salesReceipts = new ArrayList<>();

    public CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public SalesReceipt getSalesReceipt(long timeMillis) {
        return getSalesReceipts().stream()
            .filter(sr -> sr.equalsTimeMillis(timeMillis))
            .findAny()
            .orElse(null);
    }

    public void addSalesReceipt(@NonNull SalesReceipt salesReceipt) {
        getSalesReceipts().add(salesReceipt);
    }
}
