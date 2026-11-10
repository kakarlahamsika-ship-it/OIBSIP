
package com.reservation;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Online Railway Reservation System");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 245, 255));

        JLabel title = new JLabel("RAILWAY RESERVATION");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(75, 30, 300, 35);
        panel.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(65, 105, 100, 25);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(165, 105, 200, 30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(65, 155, 100, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(165, 155, 200, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(165, 210, 120, 35);
        panel.add(loginButton);

        loginButton.addActionListener(e -> login());

        add(panel);
        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") &&
            password.equals("admin123")) {

            JOptionPane.showMessageDialog(
                this,
                "Login Successful!"
            );

            new DashboardFrame();
            dispose();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Username or Password!",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
