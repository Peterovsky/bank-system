package bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {

    @Test
    void shouldSetTransferStatusToDoneAfterRealizingTransfer() {
        Bank bank = Bank.getBankInstance();
        Transfer transfer = new Transfer(100, "123", "456");
        bank.realizeTransfer(transfer);
        assertEquals(TransferStatus.DONE, transfer.getTransferStatus());
    }

    @Test
    void shouldAddAccountToAccountList() {
        Bank bank = Bank.getBankInstance();
        PersonalAccount account = new PersonalAccount("123", 100,
                new User("John", "Doe", "123", "123"));
        assertEquals(false, bank.getAccounts().containsKey(account.getAccountNumber()));
        bank.addAccount(account);
        assertEquals(true, bank.getAccounts().containsKey(account.getAccountNumber()));
        assertEquals(true, bank.getAccounts().containsValue(account));
    }

    @Test
    void shouldAddUserToUserList() {
        Bank bank = Bank.getBankInstance();
        User user = new User("John", "Doe", "123", "123");
        assertEquals(false, bank.getUsers().containsKey(user.getPesel()));
        bank.addUser(user);
        assertEquals(true, bank.getUsers().containsKey(user.getPesel()));
        assertEquals(true, bank.getUsers().containsValue(user));
    }
}
