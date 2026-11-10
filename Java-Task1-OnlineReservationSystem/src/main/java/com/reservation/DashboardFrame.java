
package com.reservation;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Online Railway Reservation System");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(235, 242, 250));

        JLabel title = new JLabel("RAILWAY RESERVATION");
        title.setBounds(120, 35, 380, 45);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(15, 45, 85));
        add(title);

        JLabel subtitle = new JLabel("Welcome! Manage Your Journey");
        subtitle.setBounds(170, 85, 300, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        add(subtitle);

        JButton bookButton = new JButton("BOOK TICKET");
        bookButton.setBounds(180, 140, 240, 45);
        styleButton(bookButton);
        add(bookButton);

        JButton pnrButton = new JButton("PNR STATUS");
        pnrButton.setBounds(180, 205, 240, 45);
        styleButton(pnrButton);
        add(pnrButton);

        JButton cancelButton = new JButton("CANCEL TICKET");
        cancelButton.setBounds(180, 270, 240, 45);
        styleButton(cancelButton);
        add(cancelButton);

        bookButton.addActionListener(e -> {
            new ReservationFrame();
            dispose();
        });

        pnrButton.addActionListener(e -> {
            new PNRFrame();
            dispose();
        });

        cancelButton.addActionListener(e -> {
            new CancellationFrame();
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(20, 65, 120));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
    }
}
