package bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonalAccountTest {

    @Test
    void shouldReduceBalanceAfterExternalTransfer() {
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        double balanceBeforeTransfer = account.getBalance();
        account.sendTransfer(50, "123456");
        assertEquals(balanceBeforeTransfer - 50, account.getBalance());
    }

    @Test
    void shouldNotLetRealizeTransferIfAmountIsHigherThanBalance() {
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        double balanceBeforeTransfer = account.getBalance();
        account.sendTransfer(150, "123456");
        assertEquals(balanceBeforeTransfer, account.getBalance());
    }

    @Test
    void shouldIncreaseAccountBalanceAfterReceivingTransfer() {
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        double balanceBeforeTransfer = account.getBalance();
        account.receiveTransfer(new Transfer(50, "456", "123"));
        assertEquals(balanceBeforeTransfer + 50, account.getBalance());
    }

    @Test
    void shouldReduceBalanceAfterPayingMaintenanceFee() {
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        double balanceBeforePay = account.getBalance();
        account.payAccountMaintenanceFee();
        assertEquals(balanceBeforePay - account.getMaintenanceFee(), account.getBalance());
    }

    @Test
    void shouldNotPermitToSendTransferWithNegativeAmount() {
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        assertThrows(IllegalArgumentException.class, () -> account.sendTransfer(-50, "123"));
    }

    @Test
    void shouldNotPermitToSendTransferWithZeroAmount() {
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        assertThrows(IllegalArgumentException.class, () -> account.sendTransfer(0, "123"));
    }

    @Test
    void shouldReduceBalanceAfterToppingUpOwnerPhone() {
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        double balanceBeforeTopUp = account.getBalance();
        account.topUpOwnerPhone(50);
        assertEquals(balanceBeforeTopUp - 50, account.getBalance());
    }

    @Test
    void shouldNotPermitToTopUpOwnerPhoneWithNegativeAmount() {
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        assertThrows(IllegalArgumentException.class, () -> account.topUpOwnerPhone(-50));
    }

    @Test
    void shouldNotPermitToTopUpOwnerPhoneWithZeroAmount() {
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        assertThrows(IllegalArgumentException.class, () -> account.topUpOwnerPhone(0));
    }
}
