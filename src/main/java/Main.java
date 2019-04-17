import bank.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Creating a bank instance
        Bank bank = Bank.getBankInstance();

//        // Example data
//
//        bank.addUser(new User("Jan", "Kowalski", "111", 123456789));
//        bank.addUser(new User("Tomasz", "Nowak", "222", 456789123));
//
//        bank.addAccount(new PersonalAccount("111", 3000.00, bank.getUserByPesel("111")));
//        bank.addAccount(new CompanyAccount("112", 50000.00, bank.getUserByPesel("111"), "Janexpol"));
//        bank.addAccount(new PersonalAccount("113", 10000.00, bank.getUserByPesel("222")));
//        bank.addAccount(new SavingsAccount("113", 10000.00, bank.getUserByPesel("222"), 0.012));
//        bank.addAccount(new SavingsAccount("114", 10000.00, bank.getUserByPesel("222"), 0.012));
//
//        bank.showUserList();
//        bank.showAccountList();

        // Simple text interface

        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nCOMMANDS:\n " +
                    "1 - Show user details by pesel\n " +
                    "2 - Show account details by account number\n " +
                    "3 - Show all users details\n " +
                    "4 - Show all accounts details\n " +
                    "5 - Create user\n " +
                    "6 - Create account \n " +
                    "7 - Make operations on selected account\n " +
                    "8 - Charge all accounts with maintenance fee\n " +
                    "0 - Exit application\n" +
                    "Enter number of command: ");
            switch (sc.nextInt()) {
                case 1: // Show user details by pesel
                    System.out.println("Enter user PESEL: ");
                    System.out.println(bank.getUserByPesel(sc.next()).toString());
                    break;
                case 2: // Show account details by account number
                    System.out.println("Enter account number: ");
                    System.out.println(bank.getAccountByNumber(sc.next()).toString());
                    break;
                case 3: // Show all users details
                    bank.showUserList();
                    break;
                case 4: // Show all accounts detail
                    bank.showAccountList();
                    break;
                case 5: // Create user
                    String imie, nazwisko, pesel;
                    String phoneNumber;
                    System.out.println("CREATING USER:");
                    System.out.print("First name: ");
                    imie = sc.next();
                    System.out.print("Last name: ");
                    nazwisko = sc.next();
                    System.out.print("PESEL: ");
                    pesel = sc.next();
                    System.out.print("Phone number: ");
                    phoneNumber = sc.next();
                    bank.addUser(new User(imie, nazwisko, pesel, phoneNumber));
                    break;
                case 6: { // Create account
                    System.out.println("ACCONUT TYPES:\n " +
                            "1 - Company account\n " +
                            "2 - Personal account\n " +
                            "3 - Savings account\n" +
                            "Select what type of account you would like to create:");
                    int accountChoice = sc.nextInt();
                    while (accountChoice < 0 || accountChoice > 3) {
                        System.out.println("You chose wrong number of type. Try again: ");
                        accountChoice = sc.nextInt();
                    }

                    System.out.print("Account number: ");
                    String accountNumber = sc.next();
                    System.out.print("Founds: ");
                    double founds = sc.nextDouble();
                    System.out.print("User pesel: ");
                    User user = bank.getUserByPesel(sc.next());

                    switch (accountChoice) {
                        case 1:
                            System.out.print("Company name: ");
                            String companyName = sc.next();
                            bank.addAccount(new CompanyAccount(accountNumber, founds, user, companyName));
                            break;
                        case 2:
                            bank.addAccount(new PersonalAccount(accountNumber, founds, user));
                            break;
                        case 3:
                            System.out.print("Saving rate: ");
                            double savingRate = sc.nextDouble();
                            bank.addAccount(new SavingsAccount(accountNumber, founds, user, savingRate));
                            break;
                    }

                }
                break;

                case 7: { // Make operations on selected account
                    System.out.print("Enter account number: ");
                    String accountNumber = sc.next();

                    // Operations on company account
                    if (bank.getAccountByNumber(accountNumber).getClass() == CompanyAccount.class) {
                        CompanyAccount selectedAccount = (CompanyAccount) bank.getAccountByNumber(accountNumber);
                        boolean exit2 = false;
                        while (!exit2) {
                            System.out.println("OPERATION TYPES:\n " +
                                    "1 - Send transfer\n " +
                                    "2 - Charge account with maintenance fee\n " +
                                    "3 - Get account income registry\n " +
                                    "4 - Get account outcome registry\n " +
                                    "5 - Add account owner employee\n " +
                                    "6 - Pay salaries to all owner employees\n" +
                                    "0 - Exit\n " +
                                    "Select what operation you would like to do: ");

                            switch (sc.nextInt()) {
                                case 1:
                                    System.out.print("Enter amount of founds: ");
                                    double amountOfFounds = sc.nextDouble();
                                    System.out.print("Enter transfer receiver account number: ");
                                    String receiverAccountNumber = sc.next();
                                    selectedAccount.sendTransfer(amountOfFounds, receiverAccountNumber);
                                    break;
                                case 2:
                                    selectedAccount.payAccountMaintenanceFee();
                                    break;
                                case 3:
                                    System.out.println(selectedAccount.incomeRegistryToString());
                                    break;
                                case 4:
                                    System.out.println(selectedAccount.outcomeRegistryToString());
                                    break;
                                case 5:
                                    System.out.println("Enter employee account number: ");
                                    String employeeAccountNumber = sc.next();
                                    System.out.println("Enter employee salary: ");
                                    double employeeSalary = sc.nextDouble();
                                    selectedAccount.addEmployee(new Employee(employeeAccountNumber, employeeSalary));
                                    break;
                                case 6:
                                    selectedAccount.paySalaryToAllEmployees();
                                    break;
                                case 0:
                                    exit2 = true;
                            }
                        }

                    // Operations on personal account
                    } else if (bank.getAccountByNumber(accountNumber).getClass() == PersonalAccount.class) {
                        PersonalAccount selectedAccount = (PersonalAccount) bank.getAccountByNumber(accountNumber);
                        boolean exit2 = false;
                        while (!exit2) {
                            System.out.println("OPERATION TYPES:\n " +
                                    "1 - Send transfer\n " +
                                    "2 - Charge account with maintenance fee\n " +
                                    "3 - Get account income registry\n " +
                                    "4 - Get account outcome registry\n " +
                                    "5 - Top-up the phone of account owner\n " +
                                    "0 - Exit\n " +
                                    "Select what operation you would like to do: ");

                            switch (sc.nextInt()) {
                                case 1:
                                    System.out.print("Enter amount of founds: ");
                                    double amountOfFounds = sc.nextDouble();
                                    System.out.print("Enter transfer receiver account number: ");
                                    String receiverAccountNumber = sc.next();
                                    selectedAccount.sendTransfer(amountOfFounds, receiverAccountNumber);
                                    break;
                                case 2:
                                    selectedAccount.payAccountMaintenanceFee();
                                    break;
                                case 3:
                                    System.out.println(selectedAccount.incomeRegistryToString());
                                    break;
                                case 4:
                                    System.out.println(selectedAccount.outcomeRegistryToString());
                                    break;
                                case 5:
                                    System.out.print("Enter amount of top-up:");
                                    selectedAccount.topUpYourPhone(sc.nextDouble());
                                    break;
                                case 0:
                                    exit2 = true;
                            }
                        }

                    // Operations on savings  account
                    } else {
                        SavingsAccount selectedAccount = (SavingsAccount) bank.getAccountByNumber(accountNumber);
                        boolean exit2 = false;
                        while (!exit2) {
                            System.out.println("OPERATION TYPES:\n " +
                                    "1 - Send transfer\n " +
                                    "2 - Charge account with maintenance fee\n " +
                                    "3 - Get account income registry\n " +
                                    "4 - Get account outcome registry\n " +
                                    "5 - Add interests to the account\n " +
                                    "0 - Exit\n " +
                                    "Select what operation you would like to do: ");

                            switch (sc.nextInt()) {
                                case 1:
                                    System.out.print("Enter amount of founds: ");
                                    double amountOfFounds = sc.nextDouble();
                                    System.out.print("Enter transfer receiver account number: ");
                                    String receiverAccountNumber = sc.next();
                                    selectedAccount.sendTransfer(amountOfFounds, receiverAccountNumber);
                                    break;
                                case 2:
                                    selectedAccount.payAccountMaintenanceFee();
                                    break;
                                case 3:
                                    System.out.println(selectedAccount.incomeRegistryToString());
                                    break;
                                case 4:
                                    System.out.println(selectedAccount.outcomeRegistryToString());
                                    break;
                                case 5:
                                    selectedAccount.addInterests();
                                    break;
                                case 0:
                                    exit2 = true;
                            }
                        }
                    }
                }
                break;
                case 8: // Charge all accounts with maintenance fee
                    for (Account account : bank.getAccountsMap().values()) {
                        account.payAccountMaintenanceFee();
                    }
                    break;

                case 0:
                    exit = true;
                    break;
            }
        }


    }
}
