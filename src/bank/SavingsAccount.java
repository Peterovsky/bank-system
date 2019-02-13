package bank;

public class SavingsAccount extends Account {

    private double savingRate;

    public SavingsAccount(String accountNumber, double amountOfFunds, User owner, double savingRate) {
        super(accountNumber, amountOfFunds, owner);
        this.savingRate = savingRate;
    }

    // Method that adds interests to account balance. Interests are calculated on the basis of savingRate.
    public void addInterests() {
        double interestsValue = getBalance() * savingRate;
        addFunds(interestsValue);
        System.out.println(interestsValue + " of interests has been added to account with number " + getAccountNumber());
    }

    @Override
    public void payAccountMaintenanceFee() {
        double maintenanceFee = 1.0;
        removeFunds(maintenanceFee);
        System.out.println("Account with number " + getAccountNumber() + " has been charged with maintenance fee: " + maintenanceFee);
    }

    @Override
    public String toString() {
        return super.toString() + ", Saving Rate: " + savingRate + "]";
    }
}
