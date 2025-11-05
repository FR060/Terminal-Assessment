package terminalassess;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class PayrollFrame extends JFrame {

    private JTable table;

    public PayrollFrame() {
        setTitle("Employee Payroll");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable(new DefaultTableModel(new String[]{"Name", "Position", "Salary"}, 0));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton calcBtn = new JButton("Calculate Payroll");
        calcBtn.addActionListener(e -> loadPayroll());
        add(calcBtn, BorderLayout.SOUTH);
    }

    private void loadPayroll() {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(new Object[]{data[0], data[1], data[4]});
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading payroll data.");
        }
    }
}
