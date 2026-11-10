
package com.reservation;

public class App {

    public static void main(String[] args) {

        // Initialize database
        DBConnection.initializeDatabase();

        // Open login screen
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}
