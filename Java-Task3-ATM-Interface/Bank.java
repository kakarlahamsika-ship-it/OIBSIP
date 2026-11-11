
import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;

    // Constructor
    public Bank() {

        accounts = new ArrayList<>();

        // Creating sample bank accounts
        accounts.add(new Account(
            "1001", "Rahul", 1234, 10000
        ));

        accounts.add(new Account(
            "1002", "Priya", 4321, 15000
        ));

        accounts.add(new Account(
            "1003", "Arjun", 5678, 8000
        ));
    }

    // Find account using User ID
    public Account findAccount(String userId) {

        for (Account account : accounts) {

            if (account.getUserId().equals(userId)) {
                return account;
            }
        }

        return null;
    }

    // Authenticate user
    public Account authenticate(String userId, int pin) {

        Account account = findAccount(userId);

        if (account != null && account.verifyPin(pin)) {
            return account;
        }

        return null;
    }

    // Get all accounts
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
