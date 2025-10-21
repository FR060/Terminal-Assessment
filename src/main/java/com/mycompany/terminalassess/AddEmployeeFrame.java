package com.mycompany.terminalassess;

import javax.swing.*;
import java.awt.*;

public class AddEmployeeFrame extends JFrame {

    private JTextField nameField;
    private JTextField positionField;
    private JTextField salaryField;

    public AddEmployeeFrame() {
        setTitle("Add New Employee");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel nameLabel = new JLabel("Employee Name:");
        JLabel positionLabel = new JLabel("Position:");
        JLabel salaryLabel = new JLabel("Salary:");

        nameField = new JTextField();
        positionField = new JTextField();
        salaryField = new JTextField();

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(positionLabel);
        formPanel.add(positionField);
        formPanel.add(salaryLabel);
        formPanel.add(salaryField);
        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);

        saveButton.addActionListener(e -> saveEmployee());
        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void saveEmployee() {
        String name = nameField.getText().trim();
        String position = positionField.getText().trim();
        String salary = salaryField.getText().trim();

        if (name.isEmpty() || position.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all information!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee emp = new Employee(name, position, salary);
        EmployeeRecordsFrame.addEmployee(emp);

        JOptionPane.showMessageDialog(this,
                "Employee added successfully!\n\n" +
                        "ID: " + emp.getId() +
                        "\nName: " + emp.getName() +
                        "\nPosition: " + emp.getPosition() +
                        "\nSalary: ₱" + emp.getSalary(),
                "Success", JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }
}
