package terminalassess;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UpdateEmployeeFrame extends JFrame {

    private JTextField searchNameField, positionField, monthlyField;
    private JButton searchButton, updateButton, closeButton;
    private JLabel statusLabel;

    private String foundName = null;

    public UpdateEmployeeFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Update Employee");
        setSize(440, 260);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Update Employee", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setBounds(100, 10, 240, 25);
        add(title);

        JLabel searchLabel = new JLabel("Search Name:");
        searchLabel.setBounds(30, 50, 100, 25);
        add(searchLabel);
        searchNameField = new JTextField();
        searchNameField.setBounds(140, 50, 100, 25);
        add(searchNameField);
        searchButton = new JButton("Search");
        searchButton.setBounds(270, 48, 90, 25);
        add(searchButton);

        JLabel posLabel = new JLabel("Position:");
        posLabel.setBounds(30, 90, 100, 25);
        add(posLabel);
        positionField = new JTextField();
        positionField.setBounds(140, 90, 100, 25);
        add(positionField);

        JLabel monthlyLabel = new JLabel("Monthly Salary:");
        monthlyLabel.setBounds(30, 130, 120, 25);
        add(monthlyLabel);
        monthlyField = new JTextField();
        monthlyField.setBounds(140, 130, 100, 25);
        add(monthlyField);

        updateButton = new JButton("Update");
        updateButton.setBounds(100, 170, 110, 30);
        add(updateButton);
        closeButton = new JButton("Close");
        closeButton.setBounds(230, 170, 110, 30);
        add(closeButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(20, 205, 400, 20);
        add(statusLabel);

        searchButton.addActionListener(e -> search());
        updateButton.addActionListener(e -> update());
        closeButton.addActionListener(e -> dispose());
    }

    private void search() {
        String name = searchNameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a name to search.");
            return;
        }
        File file = new File("employees.csv");
        if (!file.exists()) { JOptionPane.showMessageDialog(this, "employees.csv not found."); return; }

        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String header = br.readLine(); 
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", 3);
                if (p.length < 3) continue;
                String nm = p[0];
                if (nm.equalsIgnoreCase(name)) {
                    positionField.setText(p[1]);
                    monthlyField.setText(p[2]);
                    found = true;
                    foundName = nm;
                    break;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading employees.csv");
            return;
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Employee not found.");
        } else {
            statusLabel.setText("Editing: " + foundName);
        }
    }

    private void update() {
        if (foundName == null) {
            JOptionPane.showMessageDialog(this, "Please search an employee first.");
            return;
        }
        String newPos = positionField.getText().trim();
        String newMonthly = monthlyField.getText().trim();
        double monthlyVal;
        try {
            monthlyVal = Double.parseDouble(newMonthly);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monthly salary must be numeric.");
            return;
        }

        File file = new File("employees.csv");
        File tmp = new File("employees_temp.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file));
             PrintWriter pw = new PrintWriter(new FileWriter(tmp))) {
            String header = br.readLine();
            if (header == null) header = "Name,Position,MonthlySalary";
            pw.println(header);
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", 3);
                if (p.length < 3) continue;
                String nm = p[0];
                if (nm.equalsIgnoreCase(foundName)) {
                    pw.println(nm + "," + newPos.replace(",", " ") + "," + String.format("%.2f", monthlyVal));
                } else {
                    pw.println(line);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error updating file.");
            return;
        }
       
        if (file.delete()) tmp.renameTo(file);
        statusLabel.setText("Updated " + foundName);
        foundName = null;
        positionField.setText("");
        monthlyField.setText("");
        searchNameField.setText("");
    }
}
