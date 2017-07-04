package payday.employee.affiliation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Inheritance
@DiscriminatorColumn
@Getter
@ToString
@EqualsAndHashCode
public abstract class AbstractAffiliation implements Affiliation {
    public static final AbstractAffiliation NONE = new AbstractAffiliation() {
    };

    @Id
    @GeneratedValue
    private Integer affiliationId;
}
