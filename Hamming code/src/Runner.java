import service.HammingCodeService;
import service.HammingDecodeService;

import javax.swing.*;

class Runner {
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Thread(()->{
//            MainWindow window=new MainWindow();
//            JFrame frame=new JFrame("Hamming code");
//            frame.setContentPane(window.getMainPanel());
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.pack();
//            frame.setResizable(false);
//            frame.setVisible(true);
//        }));

        HammingCodeService codeService=new HammingCodeService();
        HammingDecodeService decodeService=new HammingDecodeService();
        System.out.println(codeService.codeString("1"));
        System.out.println(decodeService.decodeString("110"));

    }


}
