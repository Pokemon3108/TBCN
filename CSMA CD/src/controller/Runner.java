package controller;

import view.MainWindow;

import javax.swing.*;

public class Runner {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Thread(() -> {
            MainWindow window = new MainWindow();
            JFrame frame = new JFrame("CSMA/CD");
            frame.setContentPane(window.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
        }));
    }
}
