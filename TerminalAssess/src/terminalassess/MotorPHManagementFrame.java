package terminalassess;

import javax.swing.*;
import java.awt.*;

public class MotorPHManagementFrame extends JFrame {

    public MotorPHManagementFrame() {
        setTitle("MotorPH Management Portal");
        setSize(420, 420);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JLabel lbl = new JLabel("MotorPH Management Portal", SwingConstants.CENTER);
         lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setBounds(60, 10, 300, 30);
        add(lbl);

        JButton addBtn = new JButton("Add Employee");
        JButton updateBtn = new JButton("Update Employee");
        JButton deleteBtn = new JButton("Delete Employee");
        JButton viewBtn = new JButton("View Employees");
        JButton attendanceBtn = new JButton("Attendance");
        JButton payrollBtn = new JButton("Payroll");
        JButton exitBtn = new JButton("Exit");

        JButton[] buttons = {addBtn, updateBtn, deleteBtn, viewBtn, attendanceBtn, payrollBtn, exitBtn};

        int y = 60;
        for (JButton b : buttons) {
            b.setBounds(100, y, 220,  thirty());
            add(b);
            y += 45;
        }

addBtn.addActionListener(e -> new AddEmployeeFrame().setVisible(true));
updateBtn.addActionListener(e -> new UpdateEmployeeFrame().setVisible(true));
deleteBtn.addActionListener(e -> new DeleteEmployeeFrame().setVisible(true));
viewBtn.addActionListener(e -> new ViewEmployeesFrame().setVisible(true));
attendanceBtn.addActionListener(e -> new AttendanceFrame().setVisible(true));
payrollBtn.addActionListener(e -> new PayrollFrame().setVisible(true));
    }

    
    private int thirty() {
        return 36;
    }
}
