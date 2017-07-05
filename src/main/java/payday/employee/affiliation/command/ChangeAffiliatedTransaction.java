package payday.employee.affiliation.command;

import payday.employee.affiliation.AbstractAffiliation;
import payday.employee.affiliation.UnionAffiliation;

/**
 * @author myeongju.jung
 */
public class ChangeAffiliatedTransaction extends ChangeAffiliationTransaction {
    private final double dues;

    public ChangeAffiliatedTransaction(Integer empId, double dues) {
        super(empId);
        this.dues = dues;
    }

    @Override
    AbstractAffiliation getAffiliation() {
        return new UnionAffiliation(dues);
    }
}
