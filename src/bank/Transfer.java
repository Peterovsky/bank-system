package bank;

public class Transfer {

    private double amountOfFunds;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private TransferStatus transferStatus;

    Transfer(double amountOfFounds, String senderAccountNumber, String receiverAccountNumber) {
        this.amountOfFunds = amountOfFounds;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.transferStatus = TransferStatus.PREPARED;
    }

    double getAmountOfFunds() {
        return amountOfFunds;
    }

    void setAmountOfFunds(double amountOfFunds) {
        this.amountOfFunds = amountOfFunds;
    }

    String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    @Override
    public String toString() {
        return "[Amount: " + amountOfFunds +
                ", Sender: " + senderAccountNumber +
                ", Receiver: " + receiverAccountNumber +
                ", Status: " + transferStatus + "]";
    }
}
