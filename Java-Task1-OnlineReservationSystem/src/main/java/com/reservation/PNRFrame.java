
package com.reservation;

import javax.swing.*;
import java.awt.*;

public class PNRFrame extends JFrame {

    private JTextField pnrField;

    public PNRFrame() {

        setTitle("PNR Status");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(240, 245, 255));

        JLabel title = new JLabel("CHECK PNR STATUS");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(105, 30, 260, 35);
        panel.add(title);

        JLabel label = new JLabel("Enter PNR Number:");
        label.setBounds(55, 100, 150, 30);
        panel.add(label);

        pnrField = new JTextField();
        pnrField.setBounds(205, 100, 180, 30);
        panel.add(pnrField);

        JButton searchButton = new JButton("SEARCH");
        searchButton.setBounds(155, 155, 120, 35);
        panel.add(searchButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(155, 205, 120, 30);
        panel.add(backButton);

        searchButton.addActionListener(e -> checkPNR());

        backButton.addActionListener(e -> {
            new DashboardFrame();
            dispose();
        });

        add(panel);
        setVisible(true);
    }

    private void checkPNR() {

        String pnr = pnrField.getText().trim();

        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please enter PNR number!"
            );
            return;
        }

        String sql = "SELECT * FROM reservations WHERE pnr = ?";

        try (java.sql.Connection con = DBConnection.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pnr.toUpperCase());

            try (java.sql.ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this,
                        "TICKET FOUND!\n\n" +
                        "PNR: " + rs.getString("pnr") +
                        "\nPassenger: " + rs.getString("passenger_name") +
                        "\nTrain: " + rs.getString("train_name") +
                        "\nTrain Number: " + rs.getString("train_number") +
                        "\nClass: " + rs.getString("class_type") +
                        "\nJourney Date: " + rs.getString("journey_date") +
                        "\nSource: " + rs.getString("source") +
                        "\nDestination: " + rs.getString("destination")
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        this, "PNR not found!"
                    );
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this, "Error: " + e.getMessage()
            );
        }
    }
}

