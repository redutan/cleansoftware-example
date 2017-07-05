package payday.employee.affiliation.command;

import payday.employee.affiliation.AbstractAffiliation;

/**
 * @author myeongju.jung
 */
public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    ChangeUnaffiliatedTransaction(Integer empId) {
        super(empId);
    }

    @Override
    AbstractAffiliation getAffiliation() {
        return null;
    }
}
