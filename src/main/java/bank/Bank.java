package bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    // Implementation of bank with a Singleton design pattern

    private static Bank instance;

    private Bank() {
    }

    public static Bank getBankInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    private Map<String, Account> accounts = new HashMap<String, Account>();            // Account base
    private Map<String, User> users = new HashMap<String, User>();                  // User base

    // Method that adds user to user base
    public void addUser(User user) {
        if (!users.containsKey(user.getPesel())) {
            users.put(user.getPesel(), user);
            System.out.println("Success! Created user: " + user.toString());
        } else {
            System.out.println("User with this PESEL already exists!");
        }
    }

    // Method that adds account to account base
    public void addAccount(Account account) {
        if (!accounts.containsKey(account.getAccountNumber())) {
            accounts.put(account.getAccountNumber(), account);
            System.out.println("Success! Created account: " + account.toString());
        } else {
            System.out.println("Account with this number already exists!");
        }
    }

    // Method that realize transfer in bank system
    // If receiver is in our bank, method transfers money to him
    // If receiver isn't in our bank, transfer is classified as a external.

    void realizeTransfer(Transfer transfer) {
        try {
            accounts.get(transfer.getReceiverAccountNumber()).receiveTransfer(transfer);
            System.out.println("Internal transfer was made " + transfer.toString());
        } catch (NullPointerException e) {
            System.out.println("External transfer was made " + transfer.toString());
        }
        transfer.setTransferStatus(TransferStatus.DONE);
    }

    public User getUserByPesel(String pesel) {
        return users.get(pesel);
    }

    public Account getAccountByNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    // Method that shows list of all users with details about them
    public void showUserList() {
        System.out.println("\nUSER LIST:");
        for (User user : users.values()) {
            System.out.println(user.toString());
        }
    }

    // Method that shows list of all accounts with details about them
    public void showAccountList() {
        System.out.println("\nACCOUNT LIST:");
        for (Account account : accounts.values()) {
            System.out.println(account.toString());
        }
    }
}
