package payday.employee.classification.command;


import payday.employee.Employee;
import payday.employee.classification.AbstractPaymentClassification;
import payday.employee.command.ChangeEmployeeTransaction;
import payday.employee.schedule.AbstractPaymentSchedule;

@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    protected final void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    abstract AbstractPaymentSchedule getSchedule();

    abstract AbstractPaymentClassification getClassification();
}
