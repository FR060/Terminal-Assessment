package terminalassess;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class UpdateEmployeeFrame extends JFrame {

    private JTextField nameField, positionField, rateField, hoursField;
    private JButton searchButton, updateButton, closeButton;
    private JLabel statusLabel;

    private String employeeName;

    public UpdateEmployeeFrame() {
        setTitle("Update Employee");
        setSize(400, 320);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitle = new JLabel("Update Employee Record", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setBounds(60, 20, 260, 25);
        add(lblTitle);

        JLabel lblName = new JLabel("Employee Name:");
        lblName.setBounds(40, 70, 120, 25);
        add(lblName);

        nameField = new JTextField();
        nameField.setBounds(170, 70, 160, 25);
        add(nameField);

        searchButton = new JButton("Search");
        searchButton.setBounds(140, 110, 100, 25);
        add(searchButton);

        JLabel lblPosition = new JLabel("Position:");
        lblPosition.setBounds(40, 150, 120, 25);
        add(lblPosition);

        positionField = new JTextField();
        positionField.setBounds(170, 150, 160, 25);
        add(positionField);

        JLabel lblRate = new JLabel("Rate per Hour:");
        lblRate.setBounds(40, 180, 120, 25);
        add(lblRate);

        rateField = new JTextField();
        rateField.setBounds(170, 180, 160, 25);
        add(rateField);

        JLabel lblHours = new JLabel("Hours Worked:");
        lblHours.setBounds(40, 210, 120, 25);
        add(lblHours);

        hoursField = new JTextField();
        hoursField.setBounds(170, 210, 160, 25);
        add(hoursField);

        updateButton = new JButton("Update");
        updateButton.setBounds(80, 250, 100, 30);
        add(updateButton);

        closeButton = new JButton("Close");
        closeButton.setBounds(200, 250, 100, 30);
        add(closeButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(40, 285, 300, 25);
        statusLabel.setForeground(Color.BLUE);
        add(statusLabel);

        searchButton.addActionListener(e -> searchEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        closeButton.addActionListener(e -> dispose());
    }

    private void searchEmployee() {
        employeeName = nameField.getText().trim();
        if (employeeName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter employee name to search.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            boolean found = false;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equalsIgnoreCase(employeeName)) {
                    positionField.setText(data[1]);
                    rateField.setText(data[2]);
                    hoursField.setText(data[3]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Employee not found!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file.");
        }
    }

    private void updateEmployee() {
        String newPosition = positionField.getText().trim();
        String newRate = rateField.getText().trim();
        String newHours = hoursField.getText().trim();

        if (employeeName == null || employeeName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please search an employee first.");
            return;
        }

        try {
            File file = new File("employees.csv");
            File tempFile = new File("employees_temp.csv");

            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line;
            pw.println("Name,Position,RatePerHour,HoursWorked,Salary");
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equalsIgnoreCase(employeeName)) {
                    double rate = Double.parseDouble(newRate);
                    double hours = Double.parseDouble(newHours);
                    double salary = rate * hours;
                    pw.println(employeeName + "," + newPosition + "," + rate + "," + hours + "," + salary);
                } else {
                    pw.println(line);
                }
            }

            br.close();
            pw.close();

            file.delete();
            tempFile.renameTo(file);

            statusLabel.setText("Employee updated successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating record.");
        }
    }
}
