package bank;

public class PersonalAccount extends Account {

    private final double MAINTENANCE_FEE = 5.0;

    public PersonalAccount(String accountNumber, double amountOfFunds, User owner) {
        super(accountNumber, amountOfFunds, owner);
    }

    // Method that top up account owner phone
    public void topUpOwnerPhone(double topUpValue) {
        if (topUpValue > 0) {
            removeFunds(topUpValue);
            System.out.println("Your phone has been topped up. Top-up value: " + topUpValue);
        } else {
            throw new IllegalArgumentException("Top up value has to be positive!");
        }
    }

    @Override
    public void payAccountMaintenanceFee() {
        removeFunds(MAINTENANCE_FEE);
        System.out.println("Account with number " + getAccountNumber() + " has been charged with maintenance fee: " + MAINTENANCE_FEE);
    }

    public double getMaintenanceFee() {
        return MAINTENANCE_FEE;
    }

    @Override
    public String toString() {
        return super.toString() + "]";
    }
}
