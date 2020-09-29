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

      //  int i=1;
      //  System.out.println(i^1);

//        StuffingService stuffingService=new StuffingService();
//        System.out.println("0101333011113330013330");
//        String str=stuffingService.stuffData("0101333011113330013330");
//        System.out.println(str);
//        str=stuffingService.deBitStuffData(str);
//        System.out.println(str);
    }
}
