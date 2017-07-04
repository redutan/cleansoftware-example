package payday.employee;

import lombok.*;
import payday.employee.affiliation.AbstractAffiliation;
import payday.employee.affiliation.Affiliation;
import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.method.AbstractPaymentMethod;
import payday.employee.schedule.AbstractPaymentSchedule;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Optional;

/**
 * @author myeongju.jung
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"classification", "method", "schedule"})
@EqualsAndHashCode(exclude = {"classification", "method", "schedule"})
public class Employee {
    @Id
    private Integer empId;
    @Setter
    private String name;
    private String address;
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private AbstractPaymentClassification classification;
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private AbstractPaymentSchedule schedule;
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private AbstractPaymentMethod method;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AbstractAffiliation affiliation;

    public Employee(@NonNull Integer empId, @NonNull String name, @NonNull String address,
                    @NonNull AbstractPaymentClassification pc, @NonNull AbstractPaymentSchedule ps, @NonNull AbstractPaymentMethod pm) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.classification = pc;
        this.schedule = ps;
        this.method = pm;
    }

    public <T extends AbstractPaymentClassification> T getClassification(Class<T> tClass) {
        return tClass.cast(this.classification);
    }

    public <T extends AbstractPaymentSchedule> T getSchedule(Class<T> tClass) {
        return tClass.cast(this.schedule);
    }

    public <T extends AbstractPaymentMethod> T getMethod(Class<T> tClass) {
        return tClass.cast(this.method);
    }

    public Affiliation getAffiliation() {
        return Optional.ofNullable(this.affiliation)
            .orElse(AbstractAffiliation.NONE);
    }

    public <T extends Affiliation> T getAffiliation(Class<T> tClass) {
        Affiliation result = getAffiliation();
        if (result == AbstractAffiliation.NONE) {
            throw new IllegalStateException("affiliation is none");
        }
        return tClass.cast(result);
    }

    public void setAffiliation(AbstractAffiliation af) {
        this.affiliation = af;
    }
}
