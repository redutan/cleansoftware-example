package payday.employee;

import lombok.*;
import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.method.AbstractPaymentMethod;
import payday.employee.schedule.AbstractPaymentSchedule;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    private String name;
    private String address;
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private AbstractPaymentClassification classification;
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private AbstractPaymentSchedule schedule;
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private AbstractPaymentMethod method;

    public Employee(@NonNull Integer empId, @NonNull String name, @NonNull String address,
                    @NonNull AbstractPaymentClassification pc, @NonNull AbstractPaymentSchedule ps, @NonNull AbstractPaymentMethod pm) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.classification = pc;
        this.schedule = ps;
        this.method = pm;
    }
}
