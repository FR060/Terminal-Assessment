package terminalassess;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AddEmployeeFrame extends JFrame {

    private JTextField nameField, positionField, monthlyField;
    private JButton saveButton, clearButton, closeButton;
    private JLabel statusLabel;

    public AddEmployeeFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Add New Employee");
        setSize(420, 220);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Add New Employee", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setBounds(80, 10, 260, 25);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Employee Name:");
        nameLabel.setBounds(30, 50, 120, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(160, 50, 220, 25);
        add(nameField);

        JLabel posLabel = new JLabel("Position:");
        posLabel.setBounds(30, 85, 120, 25);
        add(posLabel);
        positionField = new JTextField();
        positionField.setBounds(160, 85, 220, 25);
        add(positionField);

        JLabel monthlyLabel = new JLabel("Monthly Salary (₱):");
        monthlyLabel.setBounds(30, 120, 120, 25);
        add(monthlyLabel);
        monthlyField = new JTextField();
        monthlyField.setBounds(160, 120, 220, 25);
        add(monthlyField);

saveButton = new JButton("Save");
saveButton.setBounds(40, 152, 80, 20);
add(saveButton);

clearButton = new JButton("Clear");
clearButton.setBounds(155, 152, 80, 20);
add(clearButton);

closeButton = new JButton("Close");
closeButton.setBounds(270, 152, 80, 20);
add(closeButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(20, 185, 380, 20);
        add(statusLabel);

        saveButton.addActionListener(e -> saveEmployee());
        clearButton.addActionListener(e -> clearFields());
        closeButton.addActionListener(e -> dispose());
    }

    private void saveEmployee() {
        String name = nameField.getText().trim();
        String pos = positionField.getText().trim();
        String monthly = monthlyField.getText().trim();

        if (name.isEmpty() || pos.isEmpty() || monthly.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double monthlySalary;
        try {
            monthlySalary = Double.parseDouble(monthly);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monthly salary must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    
        File file = new File("employees.csv");
        boolean exists = file.exists();
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            if (!exists) {
                pw.println("Name,Position,MonthlySalary");
            }

            pw.println(name.replace(",", " ") + "," + pos.replace(",", " ") + "," + String.format("%.2f", monthlySalary));
            statusLabel.setText("Employee saved.");
            clearFields();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing employees.csv", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        positionField.setText("");
        monthlyField.setText("");
        statusLabel.setText("");
    }
}
