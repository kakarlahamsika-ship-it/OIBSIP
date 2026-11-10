
package com.reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:reservation.db";

   public static Connection getConnection() throws Exception {
    Class.forName("org.sqlite.JDBC");
    return DriverManager.getConnection(URL);
}
    public static void initializeDatabase() {

        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {

            // Create trains table
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS trains (" +
                "train_number TEXT PRIMARY KEY, " +
                "train_name TEXT NOT NULL)"
            );

            // Create reservations table
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS reservations (" +
                "pnr TEXT PRIMARY KEY, " +
                "passenger_name TEXT NOT NULL, " +
                "train_number TEXT NOT NULL, " +
                "train_name TEXT NOT NULL, " +
                "class_type TEXT NOT NULL, " +
                "journey_date TEXT NOT NULL, " +
                "source TEXT NOT NULL, " +
                "destination TEXT NOT NULL)"
            );

            // Insert sample trains
            String sql =
                "INSERT OR IGNORE INTO trains VALUES (?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, "12727");
                ps.setString(2, "Godavari Express");
                ps.executeUpdate();

                ps.setString(1, "12728");
                ps.setString(2, "Godavari Express");
                ps.executeUpdate();
            }

            System.out.println("Database connected successfully!");
            System.out.println("Tables created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

