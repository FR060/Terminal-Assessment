package terminalassess;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class ViewEmployeesFrame extends JFrame {

    private JTable table;

    public ViewEmployeesFrame() {
        setTitle("View Employees");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable(new DefaultTableModel(new String[]{"Name", "Position", "Rate/hr", "Hours", "Salary"}, 0));
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadEmployees();
    }

    private void loadEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                model.addRow(line.split(","));
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading employees file.");
        }
    }
}
