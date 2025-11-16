package terminalassess;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PayrollFrame extends JFrame {

    private JTable table;
    private static final DateTimeFormatter TF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public PayrollFrame() {
        setTitle("Employee Payroll (attendance-based)");
        setSize(820, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable(new DefaultTableModel(new String[]{
                "Name", "Monthly Salary", "Hours Worked", "Hourly Rate", "Computed Salary"
        }, 0));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton calcBtn = new JButton("Calculate Payroll (from attendance)");
        calcBtn.addActionListener(e -> calculatePayroll());
        add(calcBtn, BorderLayout.SOUTH);
    }

    
    private String formatHours(double decimalHours) {
        int totalMinutes = (int) Math.round(decimalHours * 60);
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        if (hours > 0 && minutes > 0)
            return hours + "h " + minutes + "m";
        else if (hours > 0)
            return hours + "h";
        else
            return minutes + "m";
    }

    private void calculatePayroll() {

      
        Map<String, Double> monthly = new HashMap<>();
        File empf = new File("employees.csv");
        if (!empf.exists()) {
            JOptionPane.showMessageDialog(this, "employee.csv not found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(empf))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", 3);
                if (p.length < 3) continue;
                monthly.put(p[0], Double.parseDouble(p[2]));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error reading employees.csv");
            return;
        }

       
        Map<String, Double> hoursWorked = new HashMap<>();
        Map<String, LocalDateTime> lastIn = new HashMap<>();

        File atf = new File("attendance.csv");
        if (!atf.exists()) {
            JOptionPane.showMessageDialog(this, "attendance.csv not found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(atf))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", 3);
                if (p.length < 3) continue;

                String name = p[0];
                String type = p[1];
                LocalDateTime ts;

                try {
                    ts = LocalDateTime.parse(p[2], TF);
                } catch (Exception ex) {
                    continue;
                }

                if (type.equalsIgnoreCase("IN")) {
                    lastIn.put(name, ts);
                } else if (type.equalsIgnoreCase("OUT")) {
                    if (lastIn.containsKey(name)) {
                        LocalDateTime in = lastIn.remove(name);
                        Duration d = Duration.between(in, ts);

                        double mins = d.toMinutes();
                        if (mins < 0) mins = 0;

                        hoursWorked.put(name, hoursWorked.getOrDefault(name, 0.0) + (mins / 60.0));
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error reading attendance.csv");
            return;
        }

      
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (String name : monthly.keySet()) {

            double msal = monthly.getOrDefault(name, 0.0);
            double hrs = hoursWorked.getOrDefault(name, 0.0);

            double dailyRate = msal / 22.0;
            double hourlyRate = dailyRate / 8.0;
            double computed = hrs * hourlyRate;

            model.addRow(new Object[]{
                    name,
                    String.format("%.2f", msal),
                    formatHours(hrs),
                    String.format("%.2f", hourlyRate),
                    String.format("%.2f", computed)
            });
        }

        JOptionPane.showMessageDialog(this, "Payroll calculated. Rows: " + model.getRowCount());
    }
}
