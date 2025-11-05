package terminalassess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddEmployeeFrame extends JFrame {

    private JTextField nameField, positionField, rateField, hoursField;
    private JButton saveButton, clearButton, closeButton;
    private JLabel statusLabel;

    public AddEmployeeFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Add New Employee");
        setSize(400, 320);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Add New Employee", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setBounds(80, 20, 220, 25);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Employee Name:");
        nameLabel.setBounds(40, 70, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 70, 160, 25);
        add(nameField);

        JLabel posLabel = new JLabel("Employee's Position:");
        posLabel.setBounds(40, 110, 120, 25);
        add(posLabel);

        positionField = new JTextField();
        positionField.setBounds(170, 110, 160, 25);
        add(positionField);

        JLabel rateLabel = new JLabel("Rate per Hour:");
        rateLabel.setBounds(40, 150, 120, 25);
        add(rateLabel);

        rateField = new JTextField();
        rateField.setBounds(170, 150, 160, 25);
        add(rateField);

        JLabel hoursLabel = new JLabel("Hours Worked:");
        hoursLabel.setBounds(40, 190, 120, 25);
        add(hoursLabel);

        hoursField = new JTextField();
        hoursField.setBounds(170, 190, 160, 25);
        add(hoursField);

        saveButton = new JButton("Save");
        saveButton.setBounds(50, 230, 90, 30);
        add(saveButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(155, 230, 90, 30);
        add(clearButton);

        closeButton = new JButton("Close");
        closeButton.setBounds(260, 230, 90, 30);
        add(closeButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(30, 265, 320, 25);
        statusLabel.setForeground(Color.BLUE);
        add(statusLabel);

       
        saveButton.addActionListener(e -> saveEmployee());
        clearButton.addActionListener(e -> clearFields());
        closeButton.addActionListener(e -> dispose());
    }

    private void saveEmployee() {
        String name = nameField.getText().trim();
        String position = positionField.getText().trim();
        String rate = rateField.getText().trim();
        String hours = hoursField.getText().trim();

        if (name.isEmpty() || position.isEmpty() || rate.isEmpty() || hours.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double ratePerHour = Double.parseDouble(rate);
            double hoursWorked = Double.parseDouble(hours);
            double salary = ratePerHour * hoursWorked;

            File file = new File("employees.csv");
            boolean fileExists = file.exists();
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);

            if (!fileExists) {
                pw.println("Name,Position,RatePerHour,HoursWorked,Salary");
            }
            pw.println(name + "," + position + "," + ratePerHour + "," + hoursWorked + "," + salary);
            pw.close();

            statusLabel.setText("Employee saved successfully!");
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Rate and hours must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving to file.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        positionField.setText("");
        rateField.setText("");
        hoursField.setText("");
        statusLabel.setText("");
    }
}
