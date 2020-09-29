import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow {

    StuffingService stuffingService=new StuffingService();

    private JPanel mainPanel;
    private JButton buttonSendData;

    private JTextArea transferredData;
    private JTextArea decodedData;
    private JTextArea codedData;

    private JLabel transferredDataLabel;
    private JLabel decodedDataLabel;
    private JLabel codedDataLabel;

    public MainWindow() {
        transferredData.addKeyListener(new KeyListener());
        buttonSendData.addActionListener(new ButtonListener());
        transferredData.setSize(250,20);
        decodedData.setSize(250,20);
        codedData.setSize(250,50);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    private class KeyListener extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            char inputSymbol=e.getKeyChar();
            if (inputSymbol!='0' && inputSymbol!='1') {
                e.consume();
            }
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String data=transferredData.getText();
            String codedString=stuffingService.stuffData(data);
            codedData.setText(stuffingService.toString()+codedString);

            int infoLen=stuffingService.toString().length();
            decodedData.setText(stuffingService.deBitStuffData(codedString));
            designateInsertedBits(infoLen);
        }
    }

    private void designateInsertedBits(int startOfDesignation) {
        Highlighter highlighter = codedData.getHighlighter();
        Highlighter.HighlightPainter painter =
                new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
        stuffingService.getIndexesOfStuffedBits().stream().forEach(index-> {
            try {
                highlighter.addHighlight(startOfDesignation+index,startOfDesignation+index+1, painter);
            } catch (BadLocationException e) { }
        });
    }
}


