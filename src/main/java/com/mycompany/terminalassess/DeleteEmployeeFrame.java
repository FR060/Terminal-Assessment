package com.mycompany.terminalassess;

import javax.swing.*;
import java.awt.*;

public class DeleteEmployeeFrame extends JFrame {

    private JTextField idField;

    public DeleteEmployeeFrame() {
        setTitle("Delete Employee");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel idLabel = new JLabel("Enter Employee ID:");
        idField = new JTextField();
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");

        panel.add(idLabel);
        panel.add(idField);
        panel.add(deleteButton);
        panel.add(cancelButton);

        add(panel, BorderLayout.CENTER);

        deleteButton.addActionListener(e -> deleteEmployee());
        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void deleteEmployee() {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter Employee ID!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this employee's details with ID: " + id + "?",
                "Confirm to delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = EmployeeRecordsFrame.deleteEmployee(id);
            if (deleted) {
                JOptionPane.showMessageDialog(this,
                        "Employee deleted successfully!",
                        "Deleted",
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
}
