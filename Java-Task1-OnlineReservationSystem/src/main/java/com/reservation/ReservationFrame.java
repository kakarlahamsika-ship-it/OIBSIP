
package com.reservation;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class ReservationFrame extends JFrame {

    JTextField passengerField, trainNumberField;
    JTextField trainNameField;
    JTextField sourceField, destinationField;

    JComboBox<String> classBox;
    JDateChooser dateChooser;

    public ReservationFrame() {

        setTitle("Online Railway Reservation - Booking");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(240, 245, 255));

        JLabel title = new JLabel("TICKET RESERVATION");
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setBounds(130, 20, 300, 35);
        panel.add(title);

        // Passenger Name
        addField(panel, "Passenger Name:", 85);
        passengerField = addTextField(panel, 85);

        // Train Number
        addField(panel, "Train Number:", 135);
        trainNumberField = addTextField(panel, 135);

        // Train Name
        addField(panel, "Train Name:", 185);
        trainNameField = addTextField(panel, 185);
        trainNameField.setEditable(false);

        // Class Type
        addField(panel, "Class Type:", 235);

        classBox = new JComboBox<>(
            new String[]{
                "Sleeper",
                "AC 3 Tier",
                "AC 2 Tier",
                "AC First Class"
            }
        );

        classBox.setBounds(230, 235, 200, 30);
        panel.add(classBox);

        // Journey Date - Popup Calendar
        addField(panel, "Journey Date:", 285);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setDate(new Date());

        java.util.Calendar calendar =
                java.util.Calendar.getInstance();

        calendar.set(
            java.util.Calendar.HOUR_OF_DAY, 0
        );
        calendar.set(
            java.util.Calendar.MINUTE, 0
        );
        calendar.set(
            java.util.Calendar.SECOND, 0
        );
        calendar.set(
            java.util.Calendar.MILLISECOND, 0
        );

        dateChooser.setMinSelectableDate(calendar.getTime());

        dateChooser.setBounds(230, 285, 200, 30);
        panel.add(dateChooser);

        // Source Station
        addField(panel, "Source Station:", 335);
        sourceField = addTextField(panel, 335);

        // Destination
        addField(panel, "Destination:", 385);
        destinationField = addTextField(panel, 385);

        // Book Ticket Button
        JButton bookButton = new JButton("BOOK TICKET");
        bookButton.setBounds(180, 445, 160, 35);
        panel.add(bookButton);

        // Check PNR Status Button
        JButton statusButton = new JButton("CHECK PNR STATUS");
        statusButton.setBounds(160, 490, 200, 35);
        panel.add(statusButton);

        // Back Button
        JButton backButton = new JButton("BACK TO DASHBOARD");
        backButton.setBounds(160, 535, 200, 35);
        panel.add(backButton);

        trainNumberField.addActionListener(e -> findTrain());

        bookButton.addActionListener(e -> bookTicket());

        statusButton.addActionListener(e -> checkPNR());

        backButton.addActionListener(e -> {
            new DashboardFrame();
            dispose();
        });

        add(panel);
        setVisible(true);
    }

    private void addField(
            JPanel panel, String name, int y) {

        JLabel label = new JLabel(name);
        label.setBounds(65, y, 150, 30);
        panel.add(label);
    }

    private JTextField addTextField(
            JPanel panel, int y) {

        JTextField field = new JTextField();
        field.setBounds(230, y, 200, 30);
        panel.add(field);

        return field;
    }

    // Find Train
    private void findTrain() {

        String number = trainNumberField
                .getText().trim();

        if (number.equals("12727")
                || number.equals("12728")) {

            trainNameField.setText("Godavari Express");

        } else {

            trainNameField.setText("");

            JOptionPane.showMessageDialog(
                this,
                "Train number not found!"
            );
        }
    }

    // Book Ticket
    private void bookTicket() {

        String passenger =
                passengerField.getText().trim();

        String train =
                trainNumberField.getText().trim();

        String trainName =
                trainNameField.getText().trim();

        String source =
                sourceField.getText().trim();

        String destination =
                destinationField.getText().trim();

        String travelClass =
                classBox.getSelectedItem().toString();

        // Get selected calendar date
        Date selectedDate = dateChooser.getDate();

        if (passenger.isEmpty()
                || train.isEmpty()
                || trainName.isEmpty()
                || selectedDate == null
                || source.isEmpty()
                || destination.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please fill all the fields!"
            );

            return;
        }

        // Validate passenger name
        if (!passenger.matches("[\\p{L} .'-]+")) {

            JOptionPane.showMessageDialog(
                this,
                "Enter a valid passenger name!"
            );

            return;
        }

        // Validate train number
        if (!train.equals("12727")
                && !train.equals("12728")) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid train number!"
            );

            return;
        }

        // Convert calendar date into String
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd");

        sdf.setLenient(false);

        String date = sdf.format(selectedDate);

        // Validate journey date
        java.time.LocalDate journeyDate =
                java.time.LocalDate.parse(date);

        if (journeyDate.isBefore(
                java.time.LocalDate.now())) {

            JOptionPane.showMessageDialog(
                this,
                "Journey date cannot be in the past!"
            );

            return;
        }

        // Validate source and destination
        if (source.equalsIgnoreCase(destination)) {

            JOptionPane.showMessageDialog(
                this,
                "Source and destination cannot be the same!"
            );

            return;
        }

        // Generate unique PNR
        String pnr = "PNR" +
                java.util.UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        String sql =
            "INSERT INTO reservations " +
            "(pnr, passenger_name, train_number, " +
            "train_name, class_type, journey_date, " +
            "source, destination) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, pnr);
            ps.setString(2, passenger);
            ps.setString(3, train);
            ps.setString(4, trainName);
            ps.setString(5, travelClass);
            ps.setString(6, date);
            ps.setString(7, source);
            ps.setString(8, destination);

            ps.executeUpdate();

            new TicketFrame(
    pnr,
    passenger,
    train,
    trainName,
    travelClass,
    date,
    source,
    destination
);

// Close the booking window
dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Booking Failed: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // Check PNR
    private void checkPNR() {

        String pnr = JOptionPane.showInputDialog(
            this,
            "Enter your PNR Number:"
        );

        if (pnr == null || pnr.trim().isEmpty()) {
            return;
        }

        String sql =
            "SELECT * FROM reservations WHERE pnr = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(
                1,
                pnr.trim().toUpperCase()
            );

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                        this,
                        "TICKET FOUND!\n\n" +
                        "PNR: " + rs.getString("pnr") +
                        "\nPassenger: " +
                        rs.getString("passenger_name") +
                        "\nTrain: " +
                        rs.getString("train_name") +
                        "\nTrain Number: " +
                        rs.getString("train_number") +
                        "\nClass: " +
                        rs.getString("class_type") +
                        "\nJourney Date: " +
                        rs.getString("journey_date") +
                        "\nSource: " +
                        rs.getString("source") +
                        "\nDestination: " +
                        rs.getString("destination")
                    );

                } else {

                    JOptionPane.showMessageDialog(
                        this,
                        "Invalid PNR! Ticket not found."
                    );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage()
            );
        }
    }
}
