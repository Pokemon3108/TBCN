package view;


import service.CollisionSearchService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;


public class MainWindow {

    private CollisionSearchService service = new CollisionSearchService();

    private JPanel mainPanel;
    private JButton buttonSendData;

    private JTextField inputData;
    private JTextArea outputData;
    private JTextArea status;

    private JLabel inputDataLabel;
    private JLabel outputDataLabel;
    private JLabel statusLabel;
    private JLabel errorCollison;


    public MainWindow() {
        outputData.setSize(250, 20);
        status.setSize(250, 50);
        buttonSendData.addActionListener(new ButtonListener());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            errorCollison.setVisible(false);
            inputData.setEnabled(false);
            Thread t=new Thread(new Update());
            t.start();
            // ExecutorService executor = Executors.newSingleThreadExecutor();
           // executor.execute(new Update());

            inputData.setEnabled(true);
        }

        private class Update implements Runnable{
            @Override
            public void run() {
                String data = inputData.getText();
                String receivedData="";
                String statusText = "";
                for (int i = 0; i < data.length(); ++i) {
                       int  colAmount = service.fixCollision();
                        if (colAmount<=16) {
                            statusText += String.format("'%c' : %s%n", data.charAt(i),
                                    String.join("", Collections.nCopies(colAmount, "*")));
                            status.setText(statusText);
                            receivedData+=data.charAt(i);
                            outputData.setText(receivedData);
                        }

                }
            }
        }
    }
}



