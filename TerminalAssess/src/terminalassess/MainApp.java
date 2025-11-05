package terminalassess;

public class MainApp {
    public static void main(String[] args) {
        FileInitializer.ensureFilesExist();

        javax.swing.SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
