package bank;

import java.util.ArrayList;

public class CompanyAccount extends Account {

    private String companyName;
    private ArrayList<Employee> employees = new ArrayList<Employee>();  // List of account numbers and salaries of all employees employed by account owner
    private final double MAINTENANCE_FEE = 20.0;

    public CompanyAccount(String accountNumber, double amountOfFunds, User owner, String companyName) {
        super(accountNumber, amountOfFunds, owner);
        this.companyName = companyName;
    }

    // Method that pay salary to all employees
    public void paySalaryToAllEmployees() {
        for (Employee employee : employees) {
            sendTransfer(employee.getSalary(), employee.getAccountNumber());
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee with account number " + employee.getAccountNumber() +
                " and salary " + employee.getSalary() +
                " added to employees list.");
    }

    @Override
    public void payAccountMaintenanceFee() {
        removeFunds(MAINTENANCE_FEE);
        System.out.println("Account with number " + getAccountNumber() + " has been charged with maintenance fee: " + MAINTENANCE_FEE);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public double getMaintenanceFee() {
        return MAINTENANCE_FEE;
    }

    @Override
    public String toString() {
        return super.toString() + ", Company: " + companyName + "]";
    }
}
