package com.mycompany.terminalassess;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class EmployeeRecordsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private static ArrayList<Employee> employeeList = new ArrayList<>();

    public EmployeeRecordsFrame() {
        setTitle("Stored Employee Records");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        
        JLabel headerLabel = new JLabel("Stored Employee Records", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(headerLabel, BorderLayout.NORTH);

        
        String[] columns = {"Employee ID", "Name", "Position", "Salary"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshButton = new JButton("Refresh");
        JButton closeButton = new JButton("Close");

        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadEmployees());
        closeButton.addActionListener(e -> dispose());

        loadEmployees();
        setVisible(true);
    }

  
    private void loadEmployees() {
        model.setRowCount(0);
        for (Employee emp : employeeList) {
            model.addRow(new Object[]{
                    emp.getId(),
                    emp.getName(),
                    emp.getPosition(),
                    emp.getSalary()
            });
        }
    }

  
    public static void addEmployee(Employee emp) {
        employeeList.add(emp);
    }


    public static boolean updateEmployee(String id, String newPosition, String newSalary) {
        for (Employee emp : employeeList) {
            if (emp.getId().equals(id)) {
                emp.setPosition(newPosition);
                emp.setSalary(newSalary);
                return true;
            }
        }
        return false;
    }

    
    public static boolean deleteEmployee(String id) {
        return employeeList.removeIf(emp -> emp.getId().equals(id));
    }
}
