package payday.employee.command;

import payday.employee.Employee;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {

    private final String address;

    public ChangeAddressTransaction(Integer empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    protected void change(Employee e) {
        e.setAddress(address);
    }
}
