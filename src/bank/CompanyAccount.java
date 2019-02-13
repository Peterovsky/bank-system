package bank;

import java.util.ArrayList;

public class CompanyAccount extends Account {

    private String companyName;
    private ArrayList<Employee> employees = new ArrayList<Employee>();  // List of account numbers and salaries of all employees employed by account owner


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
        double maintenanceFee = 20.0;
        removeFunds(maintenanceFee);
        System.out.println("Account with number " + getAccountNumber() + " has been charged with maintenance fee: " + maintenanceFee);
    }

    @Override
    public String toString() {
        return super.toString() + ", Company: " + companyName + "]";
    }
}
