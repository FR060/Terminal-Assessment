package com.mycompany.terminalassess;

import javax.swing.*;
import java.awt.*;

public class UpdateEmployeeFrame extends JFrame {

    private JTextField idField;
    private JTextField newPositionField;
    private JTextField newSalaryField;

    public UpdateEmployeeFrame() {
        setTitle("Update Employee");
        setSize(400, 230);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel idLabel = new JLabel("Employee ID:");
        JLabel positionLabel = new JLabel("New Position:");
        JLabel salaryLabel = new JLabel("New Salary:");

        idField = new JTextField();
        newPositionField = new JTextField();
        newSalaryField = new JTextField();

        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(positionLabel);
        formPanel.add(newPositionField);
        formPanel.add(salaryLabel);
        formPanel.add(newSalaryField);
        formPanel.add(updateButton);
        formPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);

        updateButton.addActionListener(e -> updateEmployee());
        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void updateEmployee() {
        String id = idField.getText().trim();
        String pos = newPositionField.getText().trim();
        String salary = newSalaryField.getText().trim();

        if (id.isEmpty() || pos.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean updated = EmployeeRecordsFrame.updateEmployee(id, pos, salary);

        if (updated) {
            JOptionPane.showMessageDialog(this,
                    "Employee updated successfully!",
                    "Updated",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Employee ID not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
