package terminalassess;

import java.io.*;

public class FileInitializer {

    public static void ensureFilesExist() {
        createIfMissing("employees.csv", "Name,Position,RatePerHour,HoursWorked,Salary");
        createIfMissing("attendance.csv", "Name,Type,DateTime");
    }

    private static void createIfMissing(String filename, String header) {
        File file = new File(filename);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                pw.println(header);
            } catch (IOException e) {
                System.out.println("Error creating file: " + filename);
            }
        }
    }
}
