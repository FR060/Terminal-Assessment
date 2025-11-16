package terminalassess;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class DeleteEmployeeFrame extends JFrame {

    private JTextField nameField;
    private JButton deleteButton, closeButton;
    private JLabel statusLabel;

    public DeleteEmployeeFrame() {
        setTitle("Delete Employee");
        setSize(360, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lbl = new JLabel("Enter Employee Name:");
        lbl.setBounds(40, 30, 150, 25);
        add(lbl);

        nameField = new JTextField();
        nameField.setBounds(180, 30, 120, 25);
        add(nameField);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(60, 80, 100, 30);
        add(deleteButton);

        closeButton = new JButton("Close");
        closeButton.setBounds(180, 80, 100, 30);
        add(closeButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(30, 120, 280, 25);
        add(statusLabel);

        deleteButton.addActionListener(e -> deleteEmployee());
        closeButton.addActionListener(e -> dispose());
    }

    private void deleteEmployee() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter employee name.");
            return;
        }

        File file = new File("employees.csv");
        File tmp = new File("employees_temp.csv");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "employees.csv not found.");
            return;
        }

        boolean deleted = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file));
             PrintWriter pw = new PrintWriter(new FileWriter(tmp))) {
            String header = br.readLine();
            if (header == null) header = "Name,Position,MonthlySalary";
            pw.println(header);

            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", 3);
                if (p.length < 3) continue;
                if (!p[0].equalsIgnoreCase(name)) {
                    pw.println(line);
                } else {
                    deleted = true;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting record.");
            return;
        }

        if (file.delete()) tmp.renameTo(file);
        statusLabel.setText(deleted ? "Employee deleted." : "Employee not found.");
    }
}
