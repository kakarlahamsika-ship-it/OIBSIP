
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private String details;
    private String dateTime;

    // Constructor
    public Transaction(String type, double amount, String details) {

        this.type = type;
        this.amount = amount;
        this.details = details;

        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.dateTime = LocalDateTime.now().format(formatter);
    }

    // Display transaction details
    public void displayTransaction() {

        System.out.println("--------------------------------");
        System.out.println("Transaction Type : " + type);
        System.out.println("Amount           : Rs. " + amount);
        System.out.println("Details          : " + details);
        System.out.println("Date & Time      : " + dateTime);
        System.out.println("--------------------------------");
    }
}
