package com.mycompany.terminalassess;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginFrame() {
        setTitle("MotorPH Portal Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 220);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel userLabel = new JLabel("Admin Username:");
        JLabel passLabel = new JLabel("Admin Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        mainPanel.add(userLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passLabel);
        mainPanel.add(passwordField);

        add(mainPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.NORTH);

        loginButton.addActionListener(e -> attemptLogin());

        setVisible(true);
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.equals("AlvanO") && password.equals("2024171158")) {
            dispose();
            SwingUtilities.invokeLater(() -> new MotorPHManagementFrame());
        } else {
            messageLabel.setText("Incorrect username or password!");
        }
    }
}
