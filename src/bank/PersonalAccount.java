package bank;

public class PersonalAccount extends Account {

    public PersonalAccount(String accountNumber, double amountOfFunds, User owner) {
        super(accountNumber, amountOfFunds, owner);
    }

    // Method that top up account owner phone
    public void topUpYourPhone(double topUpValue) {
        removeFunds(topUpValue);
        System.out.println("Your phone has been topped up. Top-up value: " + topUpValue);
    }

    @Override
    public void payAccountMaintenanceFee() {
        double maintenanceFee = 5.0;
        removeFunds(maintenanceFee);
        System.out.println("Account with number " + getAccountNumber() + " has been charged with maintenance fee: " + maintenanceFee);
    }

    @Override
    public String toString() {
        return super.toString() + "]";
    }
}
