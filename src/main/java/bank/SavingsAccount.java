package bank;

public class SavingsAccount extends Account {

    private double savingRate;
    private double MAINTENANCE_FEE = 1.0;


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
        removeFunds(MAINTENANCE_FEE);
        System.out.println("Account with number " + getAccountNumber() + " has been charged with maintenance fee: " + MAINTENANCE_FEE);
    }

    public double getMaintenanceFee() {
        return MAINTENANCE_FEE;
    }

    public double getSavingRate() {
        return savingRate;
    }

    public void setSavingRate(double savingRate) {
        if (savingRate >= 0) {
            this.savingRate = savingRate;
        } else {
            throw new IllegalArgumentException("Saving rate can not be negative!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Saving Rate: " + savingRate + "]";
    }
}
