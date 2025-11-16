package terminalassess;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttendanceFrame extends JFrame {

    private JTextField nameField;
    private JButton timeInBtn, timeOutBtn;
    private JLabel statusLabel;


    private static final DateTimeFormatter TF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AttendanceFrame() {
        setTitle("Attendance Tracker");
        setSize(360, 220);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lbl = new JLabel("Employee Name:");
        lbl.setBounds(40, 40, 120, 25);
        add(lbl);

        nameField = new JTextField();
        nameField.setBounds(160, 40, 130, 25);
        add(nameField);

        timeInBtn = new JButton("Time In");
        timeInBtn.setBounds(60, 90, 100, 30);
        add(timeInBtn);

        timeOutBtn = new JButton("Time Out");
        timeOutBtn.setBounds(180, 90, 100, 30);
        add(timeOutBtn);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(30, 130, 280, 25);
        add(statusLabel);

        timeInBtn.addActionListener(e -> recordAttendance("IN"));
        timeOutBtn.addActionListener(e -> recordAttendance("OUT"));
    }

    private void recordAttendance(String type) {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter employee name first.");
            return;
        }

        try {
            File file = new File("attendance.csv");
            boolean exists = file.exists();

            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            if (!exists) {
                pw.println("Name,Type,DateTime");
            }

           
            String dateTime = LocalDateTime.now().format(TF);

            pw.println(name + "," + type + "," + dateTime);
            pw.close();

            statusLabel.setText("Attendance " + type + " recorded.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving attendance.");
        }
    }
}
