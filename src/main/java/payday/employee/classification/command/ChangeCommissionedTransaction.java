package payday.employee.classification.command;


import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.classification.CommissionedClassification;
import payday.employee.schedule.AbstractPaymentSchedule;
import payday.employee.schedule.BiWeaklySchedule;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {

    private final double salary;
    private final double commissionRate;

    public ChangeCommissionedTransaction(Integer empId, double salary, double commissionRate) {
        super(empId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    AbstractPaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    AbstractPaymentSchedule getSchedule() {
        return new BiWeaklySchedule();
    }
}
