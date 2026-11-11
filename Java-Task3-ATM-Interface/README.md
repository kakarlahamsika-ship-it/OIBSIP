
# ATM Interface Using Java

## Project Description
A console-based ATM application developed using Java.
It allows users to securely access their accounts
and perform basic banking operations.

## Features
- User authentication using User ID and PIN
- Maximum 3 login attempts
- Deposit money
- Withdraw money
- Transfer money between accounts
- View transaction history
- Exit the application

## Technologies Used
- Java
- Object-Oriented Programming
- ArrayList
- Scanner
- VS Code

## Project Structure
ATMInterface/
- Main.java
- ATM.java
- Account.java
- Bank.java
- Transaction.java

## Sample Login Credentials

| User ID | PIN |
|---------|-----|
| 1001    | 1234 |
| 1002    | 4321 |
| 1003    | 5678 |

## How to Run

1. Open the project folder.
2. Open terminal.
3. Compile the files:

   javac *.java

4. Run the application:

   java Main

## Note
This project uses in-memory storage.
Account and transaction data reset when
the application is restarted.
