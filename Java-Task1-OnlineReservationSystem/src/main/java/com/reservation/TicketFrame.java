
package com.reservation;

import javax.swing.*;
import java.awt.*;

public class TicketFrame extends JFrame {

    public TicketFrame(
            String pnr,
            String passenger,
            String train,
            String trainName,
            String travelClass,
            String date,
            String source,
            String destination) {

        setTitle("Railway Ticket Confirmation");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(240, 245, 255));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel title = new JLabel(
            "TICKET BOOKED SUCCESSFULLY!",
            SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(0, 120, 70));

        JLabel subtitle = new JLabel(
            "ONLINE RAILWAY RESERVATION",
            SwingConstants.CENTER
        );

        subtitle.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel heading = new JPanel(new GridLayout(2, 1, 5, 5));
        heading.setOpaque(false);
        heading.add(title);
        heading.add(subtitle);

        JPanel details = new JPanel(new GridLayout(0, 2, 10, 15));
        details.setBackground(Color.WHITE);
        details.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
            )
        );

        addRow(details, "PNR Number:", pnr);
        addRow(details, "Passenger Name:", passenger);
        addRow(details, "Train Number:", train);
        addRow(details, "Train Name:", trainName);
        addRow(details, "Travel Class:", travelClass);
        addRow(details, "Journey Date:", date);
        addRow(details, "Source:", source);
        addRow(details, "Destination:", destination);
        

        JButton backButton = new JButton("BACK TO DASHBOARD");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(25, 55, 110));
        backButton.setForeground(Color.WHITE);

        backButton.addActionListener(e -> {
            new DashboardFrame();
            dispose();
        });

        JButton printButton = new JButton("PRINT TICKET");

printButton.setFont(new Font("Arial", Font.BOLD, 14));

printButton.addActionListener(e -> {
    try {
        java.awt.print.PrinterJob job =
                java.awt.print.PrinterJob.getPrinterJob();

        job.setPrintable((graphics, pageFormat, pageIndex) -> {

            if (pageIndex > 0) {
                return java.awt.print.Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2 = (Graphics2D) graphics;

            g2.translate(
                pageFormat.getImageableX(),
                pageFormat.getImageableY()
            );

            mainPanel.printAll(g2);

            return java.awt.print.Printable.PAGE_EXISTS;
        });

        if (job.printDialog()) {
            job.print();
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
            this,
            "Printing failed: " + ex.getMessage()
        );
    }
});

JPanel buttonPanel = new JPanel();
buttonPanel.setOpaque(false);

buttonPanel.add(printButton);
buttonPanel.add(backButton);

mainPanel.add(heading, BorderLayout.NORTH);
mainPanel.add(details, BorderLayout.CENTER);
mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void addRow(
            JPanel panel, String labelText, String value) {

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(label);
        panel.add(valueLabel);
    }
}
