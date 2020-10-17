import javax.swing.*;
import java.io.IOException;


public class Runner {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Thread(()->{
            MainWindow window=new MainWindow();
            JFrame frame=new JFrame("Hamming code");
            frame.setContentPane(window.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
        }));

    }


}
