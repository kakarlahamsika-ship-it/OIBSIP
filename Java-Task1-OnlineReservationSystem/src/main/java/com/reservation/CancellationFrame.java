
package com.reservation;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CancellationFrame extends JFrame {

    JTextField pnrField;

    public CancellationFrame() {

        setTitle("Cancel Railway Ticket");
        setSize(450, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(230, 240, 250));

        JLabel title = new JLabel("CANCEL YOUR TICKET");
        title.setBounds(120, 30, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        JLabel pnrLabel = new JLabel("Enter PNR Number:");
        pnrLabel.setBounds(60, 100, 140, 30);
        add(pnrLabel);

        pnrField = new JTextField();
        pnrField.setBounds(200, 100, 180, 30);
        add(pnrField);

        JButton cancelButton = new JButton("CANCEL TICKET");
        cancelButton.setBounds(140, 155, 160, 35);
        add(cancelButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(160, 205, 120, 30);
        add(backButton);

        cancelButton.addActionListener(e -> cancelTicket());

        backButton.addActionListener(e -> {
            new DashboardFrame();
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cancelTicket() {

        String pnr = pnrField.getText().trim();

        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter PNR number!");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to cancel this ticket?",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM reservations WHERE pnr = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pnr);

            int result = ps.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this,
                        "Ticket cancelled successfully!");
                pnrField.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "PNR not found!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }
}
