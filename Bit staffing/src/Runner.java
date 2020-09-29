import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Runner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Thread(()->{
            MainWindow window=new MainWindow();
            JFrame frame=new JFrame("Bit stuffing");
            frame.setContentPane(window.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
        }));

    }
}
