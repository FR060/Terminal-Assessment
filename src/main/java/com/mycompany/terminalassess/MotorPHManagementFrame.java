package com.mycompany.terminalassess;

import javax.swing.*;
import java.awt.*;

public class MotorPHManagementFrame extends JFrame {

    public MotorPHManagementFrame() {
        setTitle("MotorPH Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 330);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        
        JLabel title = new JLabel("MotorPH Portal", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

      
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton addButton = new JButton("1. Add New Employee");
        JButton updateButton = new JButton("2. Update Employee");
        JButton deleteButton = new JButton("3. Delete Employee");
        JButton openStoredButton = new JButton("4. View Stored Records");
        JButton exitButton = new JButton("5. Exit");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(openStoredButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        
        addButton.addActionListener(e -> new AddEmployeeFrame());
        updateButton.addActionListener(e -> new UpdateEmployeeFrame());
        deleteButton.addActionListener(e -> new DeleteEmployeeFrame());
        openStoredButton.addActionListener(e -> new EmployeeRecordsFrame());
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}
