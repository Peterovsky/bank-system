package bank;

import java.util.ArrayList;

public abstract class Account {

    private final String accountNumber;
    private double balance; // account balance
    private User owner; // account owner
    private ArrayList<Transfer> incomeRegistry = new ArrayList<Transfer>();     // register of all income transfers
    private ArrayList<Transfer> outcomeRegistry = new ArrayList<Transfer>();    // register of all outcome transfers


    Account(String accountNumber, double amountOfFunds, User owner) {
        this.accountNumber = accountNumber;
        this.balance = amountOfFunds;
        this.owner = owner;
    }

    // Abstract method that charge a maintenance fee. It has different implementation depending on the type of account
    public abstract void payAccountMaintenanceFee();

    // Method that create outcome transfer
    public void sendTransfer(double amountOfFunds, String receiverAccountNumber) {
        if (amountOfFunds > 0) {
            if (amountOfFunds <= balance) {
                Transfer transfer = new Transfer(amountOfFunds, accountNumber, receiverAccountNumber);
                transfer.setTransferStatus(TransferStatus.IN_PROGRESS);
                Bank.getBankInstance().realizeTransfer(transfer);
                outcomeRegistry.add(transfer);
                balance -= transfer.getAmountOfFunds();
            } else {
                System.out.println("There is not enough founds to make this transfer.");
            }
        } else {
            throw new IllegalArgumentException("Amount of founds has to be positive!");
        }
    }

    // Method that receive income transfer
    void receiveTransfer(Transfer transfer) {
        incomeRegistry.add(transfer);
        balance += transfer.getAmountOfFunds();
    }

    void addFunds(double amountOfFunds) {
        balance += amountOfFunds;
    }

    void removeFunds(double amountOfFunds) {
        balance -= amountOfFunds;
    }

    String getAccountNumber() {
        return accountNumber;
    }

    double getBalance() {
        return balance;
    }

    void setBalance(double balance) {
        this.balance = balance;
    }

    User getOwner() {
        return owner;
    }

    void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "[Account number: " + accountNumber +
                ", Funds: " + balance +
                ", Owner: " + owner.getFirstName() + " " + owner.getLastName();
    }

    private String registryToString(ArrayList<Transfer> registry) {
        String returnedValue = "";
        if (registry.isEmpty()) {
            returnedValue += "\n Empty.";
        } else {
            for (Transfer transfer : registry) {
                returnedValue += "\n " + transfer.toString();
            }
        }
        return returnedValue;
    }

    public String incomeRegistryToString() {
        return registryToString(incomeRegistry);
    }

    public String outcomeRegistryToString() {
        return registryToString(outcomeRegistry);
    }
}
