package bank;

public class Employee {

    private String accountNumber;
    private double salary;

    public Employee(String accountNumber, double salary) {
        this.accountNumber = accountNumber;
        this.salary = salary;
    }

    protected String getAccountNumber() {
        return accountNumber;
    }

    protected void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    protected double getSalary() {
        return salary;
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }
}
