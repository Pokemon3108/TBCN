import service.HammingCodeService;
import service.HammingDecodeService;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow {

    private JPanel mainPanel;
    private JButton buttonSendData;

    private JTextField inputData;
    private JTextArea outputData;
    private JTextArea status;

    private JLabel inputDataLabel;
    private JLabel outputDataLabel;
    private JLabel statusLabel;

    private HammingCodeService codeService = new HammingCodeService();
    private HammingDecodeService decodeService = new HammingDecodeService();


    public MainWindow() {
        inputData.addKeyListener(new KeyListener());
        buttonSendData.addActionListener(new ButtonListener());

        outputData.setSize(250, 20);
        status.setSize(250, 50);

        status.setText(codeService.toString());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    private class KeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char inputSymbol = e.getKeyChar();
            if (inputSymbol != '0' && inputSymbol != '1'
                    || inputData.getText().length() >= codeService.getMaxLen()) {
                e.consume();
            }

        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String data = inputData.getText();
            String codedStr = codeService.codeString(data);
            status.setText(codeService.toString() + codedStr);

            int infoLen = codeService.toString().length();
            outputData.setText(decodeService.decodeString(codedStr));
            designateInsertedBits(infoLen);
        }
    }

    private void designateInsertedBits(int startOfDesignation) {
        Highlighter highlighter = status.getHighlighter();
        Highlighter.HighlightPainter painter =
                new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
        codeService.getInsertedBits().stream().forEach(index -> {
            try {
                highlighter.addHighlight(startOfDesignation + index, startOfDesignation + index + 1, painter);
            } catch (BadLocationException e) {
                System.out.println("Can not highlight bits");
            }
        });
    }
}


