package bank;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompanyAccountTest {

    @Test
    void shouldReduceBalanceAfterExternalTransfer() {
        CompanyAccount account = new CompanyAccount("123", 100,
                new User("John", "Doe", "123", "123"),"CompanyName");
        double balanceBeforeTransfer = account.getBalance();
        account.sendTransfer(50, "123456");
        assertEquals(balanceBeforeTransfer - 50, account.getBalance());
    }

    @Test
    void shouldNotLetRealizeTransferIfAmountIsHigherThanBalance() {
        CompanyAccount account = new CompanyAccount("123", 100,
                new User("John", "Doe", "123", "123"),"CompanyName");
        double balanceBeforeTransfer = account.getBalance();
        account.sendTransfer(150, "123456");
        assertEquals(balanceBeforeTransfer, account.getBalance());
    }

    @Test
    void shouldIncreaseAccountBalanceAfterReceivingTransfer() {
        CompanyAccount account = new CompanyAccount("123", 100,
                new User("John", "Doe", "123", "123"),"CompanyName");
        double balanceBeforeTransfer = account.getBalance();
        account.receiveTransfer(new Transfer(50, "456", "123"));
        assertEquals(balanceBeforeTransfer + 50, account.getBalance());
    }

    @Test
    void shouldReduceBalanceAfterPayingMaintenanceFee() {
        CompanyAccount account = new CompanyAccount("123", 100,
                new User("John", "Doe", "123", "123"),"CompanyName");
        double balanceBeforePay = account.getBalance();
        account.payAccountMaintenanceFee();
        assertEquals(balanceBeforePay - account.getMaintenanceFee(), account.getBalance());
    }

    @Test
    void shouldNotPermitToSendTransferWithNegativeAmount() {
        CompanyAccount account = new CompanyAccount("123", 100,
                new User("John", "Doe", "123", "123"),"CompanyName");
        assertThrows(IllegalArgumentException.class, () -> account.sendTransfer(-50, "123"));
    }

    @Test
    void shouldNotPermitToSendTransferWithZeroAmount() {
        CompanyAccount account = new CompanyAccount("123", 100,
                new User("John", "Doe", "123", "123"),"CompanyName");
        assertThrows(IllegalArgumentException.class, () -> account.sendTransfer(0, "123"));
    }

    @Test
    void shouldReduceBalanceAfterPayingSalaryToAllEmployees() {
        CompanyAccount account = new CompanyAccount("123", 1000,
                new User("John", "Doe", "123", "123"),"CompanyName");
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("123", 150));
        employees.add(new Employee("456", 200));
        employees.add(new Employee("789", 300));
        double balanceBeforeTransfer = account.getBalance();
        double salaries = 0;
        for (Employee employee : employees) { salaries += employee.getSalary(); }
        account.setEmployees(employees);
        account.paySalaryToAllEmployees();
        assertEquals(balanceBeforeTransfer - salaries, account.getBalance());
    }
}
