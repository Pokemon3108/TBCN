import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Thread(()->{
            MainWindow window=new MainWindow();
            JFrame frame=new JFrame("COM messenger");
            frame.setContentPane(window.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }));

    }
}