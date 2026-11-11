
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int round = 0;
        int wonRounds = 0;
        int totalAttempts = 0;
        String playAgain = "yes";

        do {
            round++;

            System.out.println("\n===== NUMBER GUESSING GAME =====");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");

            System.out.print("Choose difficulty: ");
            int choice = sc.nextInt();

            int maxNumber;
            int maxAttempts;

            if (choice == 1) {
                maxNumber = 50;
                maxAttempts = 10;
            }
            else if (choice == 2) {
                maxNumber = 100;
                maxAttempts = 7;
            }
            else if (choice == 3) {
                maxNumber = 200;
                maxAttempts = 5;
            }
            else {
                System.out.println("Invalid choice!");
                round--;
                continue;
            }

            int number = random.nextInt(maxNumber) + 1;
            int guess = 0;
            int attempts = 0;

            System.out.println("Guess a number between 1 and " + maxNumber);

            while (guess != number && attempts < maxAttempts) {

                
System.out.print("Enter your guess: ");
guess = sc.nextInt();

if (guess < 1 || guess > maxNumber) {
    System.out.println("Invalid number! Enter between 1 and " + maxNumber);
    continue;
}

attempts++;

                if (guess > number) {
                    System.out.println("Too High!");
                }
                else if (guess < number) {
                    System.out.println("Too Low!");
                }
                else {
                    System.out.println("Correct!");
                }
            }

            if (guess == number) {
    System.out.println("You won!");
    wonRounds++;
}
else {
    System.out.println("You Lost!");
    System.out.println("Correct number was: " + number);
}

totalAttempts = totalAttempts + attempts;

            System.out.println("Round " + round +
                    " — guessed in " + attempts + " attempts");

            System.out.print("Play Again? (yes/no): ");
            playAgain = sc.next();

        } while (playAgain.equalsIgnoreCase("yes"));

      System.out.println("\n===== FINAL SCORE =====");

System.out.println("Total Rounds Played: " + round);
System.out.println("Rounds Won: " + wonRounds);
System.out.println("Rounds Lost: " + (round - wonRounds));
System.out.println("Total Attempts: " + totalAttempts);

System.out.println("Thanks for playing!");

sc.close();
    }
}
