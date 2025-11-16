package terminalassess;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class ViewEmployeesFrame extends JFrame {

    static Iterable<Employee> employeeList;

    private JTable table;

    public ViewEmployeesFrame() {
        setTitle("View Employees");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable(new DefaultTableModel(new String[]{"Name", "Position", "Monthly Salary (₱)"}, 0));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> loadEmployees());
        add(refresh, BorderLayout.SOUTH);

        loadEmployees();
    }

    public void loadEmployees() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        File file = new File("employees.csv");
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String h = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", 3);
                if (p.length < 3) continue;
                model.addRow(new Object[]{p[0], p[1], p[2]});
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading employees.csv");
        }
    }
}
