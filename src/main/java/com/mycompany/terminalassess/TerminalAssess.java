package com.mycompany.terminalassess;

import javax.swing.SwingUtilities;

public class TerminalAssess {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
