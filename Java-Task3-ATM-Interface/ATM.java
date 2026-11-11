
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner sc;
    private Account currentAccount;

    // Constructor
    public ATM() {
        bank = new Bank();
        sc = new Scanner(System.in);
    }

    // Start ATM
    public void start() {

        System.out.println("================================");
        System.out.println("       WELCOME TO ATM");
        System.out.println("================================");

        int attempts = 0;
        boolean loggedIn = false;

        // Login with maximum 3 attempts
        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userId = sc.nextLine();

            System.out.print("Enter PIN: ");
            int pin = readInteger();

            currentAccount = bank.authenticate(userId, pin);

            if (currentAccount != null) {
                loggedIn = true;
                break;
            } else {
                attempts++;
                System.out.println("Invalid User ID or PIN!");
                System.out.println("Attempts remaining: "
                        + (3 - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Account access denied!");
            return;
        }

        System.out.println("\nLogin Successful!");
        System.out.println("Welcome, "
                + currentAccount.getAccountHolder());

        showMenu();
    }

    // Main ATM menu
    private void showMenu() {

        int choice;

        do {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");
            choice = readInteger();

            switch (choice) {

                case 1:
                    transactionHistory();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    System.out.println(
                        "Thank you for using our ATM!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // Deposit money
    private void deposit() {

        double amount = readAmount();

        currentAccount.deposit(amount);

        currentAccount.addTransaction(
            new Transaction(
                "DEPOSIT",
                amount,
                "Cash deposited"
            )
        );

        System.out.println("Deposit Successful!");
        System.out.println("New Balance: Rs. "
                + currentAccount.getBalance());
    }

    // Withdraw money
    private void withdraw() {

        double amount = readAmount();

        if (currentAccount.withdraw(amount)) {

            currentAccount.addTransaction(
                new Transaction(
                    "WITHDRAWAL",
                    amount,
                    "Cash withdrawn"
                )
            );

            System.out.println("Please collect your cash!");
            System.out.println("Withdrawal Successful!");

        } else {

            System.out.println("Insufficient Funds!");
        }

        System.out.println("Available Balance: Rs. "
                + currentAccount.getBalance());
    }

    // Transfer money
    private void transfer() {

        System.out.print("Enter recipient User ID: ");
        String recipientId = sc.nextLine();

        Account recipient = bank.findAccount(recipientId);

        if (recipient == null) {
            System.out.println("Recipient account not found!");
            return;
        }

        if (recipient == currentAccount) {
            System.out.println("Cannot transfer to your own account!");
            return;
        }

        double amount = readAmount();

        if (currentAccount.withdraw(amount)) {

            recipient.deposit(amount);

            currentAccount.addTransaction(
                new Transaction(
                    "TRANSFER SENT",
                    amount,
                    "Sent to " + recipientId
                )
            );

            recipient.addTransaction(
                new Transaction(
                    "TRANSFER RECEIVED",
                    amount,
                    "Received from "
                    + currentAccount.getUserId()
                )
            );

            System.out.println("Transfer Successful!");

        } else {

            System.out.println("Insufficient Funds!");
        }

        System.out.println("Available Balance: Rs. "
                + currentAccount.getBalance());
    }

    // Display transaction history
    private void transactionHistory() {

        ArrayList<Transaction> history =
                currentAccount.getTransactions();

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (history.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : history) {
            transaction.displayTransaction();
        }
    }

    // Read a valid positive amount
    private double readAmount() {

        double amount;

        while (true) {

            System.out.print("Enter amount: ");

            try {
                amount = Double.parseDouble(sc.nextLine());

                if (amount > 0 &&
                    Double.isFinite(amount)) {
                    return amount;
                }

                System.out.println(
                    "Enter a valid positive amount!"
                );

            } catch (NumberFormatException e) {

                System.out.println("Invalid amount! Try again.");
            }
        }
    }

    // Read a valid integer
    private int readInteger() {

        while (true) {

            try {
                return Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {

                System.out.print("Enter a valid number: ");
            }
        }
    }
}
