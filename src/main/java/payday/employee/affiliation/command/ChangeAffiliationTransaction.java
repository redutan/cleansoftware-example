package payday.employee.affiliation.command;

import payday.employee.Employee;
import payday.employee.affiliation.AbstractAffiliation;
import payday.employee.command.ChangeEmployeeTransaction;

/**
 * @author myeongju.jung
 */
abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    ChangeAffiliationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    protected void change(Employee e) {
        e.setAffiliation(getAffiliation());
    }

    abstract AbstractAffiliation getAffiliation();
}
