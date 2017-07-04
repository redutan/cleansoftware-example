package payday.employee.classification.command;


import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.classification.HourlyClassification;
import payday.employee.schedule.AbstractPaymentSchedule;
import payday.employee.schedule.WeaklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private final double hourlyRate;

    public ChangeHourlyTransaction(Integer empId, double hourlyRate) {
        super(empId);
        this.hourlyRate = hourlyRate;
    }

    @Override
    AbstractPaymentSchedule getSchedule() {
        return new WeaklySchedule();
    }

    @Override
    AbstractPaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }
}
