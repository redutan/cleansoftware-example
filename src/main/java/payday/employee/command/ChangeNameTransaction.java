package payday.employee.command;

import com.sun.istack.internal.NotNull;
import payday.employee.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private final String name;

    public ChangeNameTransaction(@NotNull Integer empId, String name) {
        super(empId);
        this.name = name;
    }

    @Override
    protected void change(Employee e) {
        e.setName(name);
    }
}
