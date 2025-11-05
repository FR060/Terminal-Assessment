package terminalassess;

import java.awt.Font;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private javax.swing.JButton loginButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;

    public LoginFrame() {
        initComponents();
        setTitle("MotorPH Login");
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        usernameField = new javax.swing.JTextField(15);
        passwordField = new javax.swing.JPasswordField(15);
        loginButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();


        JLabel lbl = new JLabel("Admin Login Portal", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));


        JLabel usernameLabel = new JLabel("Admin Username:");
        JLabel passwordLabel = new JLabel("Admin Password:");


        usernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);


        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setForeground(new java.awt.Color(204, 0, 0));


        loginButton.setText("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.addActionListener(evt -> attemptLogin());


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(passwordLabel)
                        .addComponent(usernameLabel))
                    .addGap(10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(50))
                .addGroup(layout.createSequentialGroup()
                    .addGap(115)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(115))
        );

        layout.setVerticalGroup(
                
            layout.createSequentialGroup()
                .addGap(35)
                .addComponent(lbl)
        
                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20)
        );

        pack();
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.equals("AlvanO") && password.equals("2024171158")) {
            dispose();
            new MotorPHManagementFrame().setVisible(true);
        } else {
            messageLabel.setText("Incorrect username or password!");
        }
    }
}
