package bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingsAccountTest {

    @Test
    void shouldReduceBalanceAfterExternalTransfer() {
        SavingsAccount account = new SavingsAccount("123", 100,
                new User("John", "Doe", "123", "123"), 0.1);
        double balanceBeforeTransfer = account.getBalance();
        account.sendTransfer(50, "123456");
        assertEquals(balanceBeforeTransfer - 50, account.getBalance());
    }

    @Test
    void shouldNotLetRealizeTransferIfAmountIsHigherThanBalance() {
        SavingsAccount account = new SavingsAccount("123", 100,
                new User("John", "Doe", "123", "123"), 0.1);
        double balanceBeforeTransfer = account.getBalance();
        account.sendTransfer(150, "123456");
        assertEquals(balanceBeforeTransfer, account.getBalance());
    }

    @Test
    void shouldIncreaseAccountBalanceAfterReceivingTransfer() {
        SavingsAccount account = new SavingsAccount("123", 100,
                new User("John", "Doe", "123", "123"), 0.1);
        double balanceBeforeTransfer = account.getBalance();
        account.receiveTransfer(new Transfer(50, "456", "123"));
        assertEquals(balanceBeforeTransfer + 50, account.getBalance());
    }

    @Test
    void shouldReduceBalanceAfterPayingMaintenanceFee() {
        SavingsAccount account = new SavingsAccount("123", 100,
                new User("John", "Doe", "123", "123"), 0.1);
        double balanceBeforePay = account.getBalance();
        account.payAccountMaintenanceFee();
        assertEquals(balanceBeforePay - account.getMaintenanceFee(), account.getBalance());
    }

    @Test
    void shouldNotPermitToSendTransferWithNegativeAmount() {
        SavingsAccount account = new SavingsAccount("123", 100,
                new User("John", "Doe", "123", "123"), 0.1);
        assertThrows(IllegalArgumentException.class, () -> account.sendTransfer(-50, "123"));
    }

    @Test
    void shouldNotPermitToSendTransferWithZeroAmount() {
        SavingsAccount account = new SavingsAccount("123", 100,
                new User("John", "Doe", "123", "123"), 0.1);
        assertThrows(IllegalArgumentException.class, () -> account.sendTransfer(0, "123"));
    }

    @Test
    void shouldIncreaseBalanceAfterAddingInterests() {
        SavingsAccount account = new SavingsAccount("123", 100,
                new User("John", "Doe", "123", "123"), 0.1);
        double balanceBeforeAddingInterests = account.getBalance();
        account.addInterests();
        assertEquals(balanceBeforeAddingInterests + balanceBeforeAddingInterests * account.getSavingRate(), account.getBalance());
    }

    @Test
    void shouldNotPermitToSetNegativeSavingRate() {
        SavingsAccount account = new SavingsAccount("123", 100,
                new User("John", "Doe", "123", "123"), 0.1);
        assertThrows(IllegalArgumentException.class, () -> account.setSavingRate(-10));
    }
}
