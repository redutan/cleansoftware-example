package payday.employee.affiliation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import payday.Paycheck;

import javax.persistence.*;

@Entity
@Inheritance
@DiscriminatorColumn
@Getter
@ToString
@EqualsAndHashCode
public abstract class AbstractAffiliation implements Affiliation {
    public static final AbstractAffiliation NONE = new AbstractAffiliation() {
        @Override
        public double calculateDeductions(Paycheck pc) {
            return 0;
        }
    };

    @Id
    @GeneratedValue
    private Integer affiliationId;
}
