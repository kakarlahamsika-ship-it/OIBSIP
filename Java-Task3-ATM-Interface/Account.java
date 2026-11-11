
import java.util.ArrayList;

public class Account {

    private String userId;
    private String accountHolder;
    private int pin;
    private double balance;

    private ArrayList<Transaction> transactions;

    // Constructor
    public Account(String userId, String accountHolder,
                   int pin, double balance) {

        this.userId = userId;
        this.accountHolder = accountHolder;
        this.pin = pin;
        this.balance = balance;

        transactions = new ArrayList<>();
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // PIN verification
    public boolean verifyPin(int enteredPin) {
        return pin == enteredPin;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Withdraw money
    public boolean withdraw(double amount) {

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }

        return false;
    }

    // Add transaction to history
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Get transaction history
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
