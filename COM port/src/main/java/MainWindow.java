import jssc.SerialPort;
import jssc.SerialPortException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Arrays;

public class MainWindow {

    private JPanel mainPanel;
    private JTextField dataToSend;
    private JTextArea params;
    private JButton buttonSendData;
    private JTextArea dataToGet;
    private JLabel error;
    private JLabel transmitterPort;
    private JLabel receiverPort;

    public MainWindow() {

        String name1 = "COM1";
        String name2 = "COM2";
        transmitterPort.setText(name1);
        receiverPort.setText(name2);
        Ports comPorts = new Ports(name1, name2);
        try {
            comPorts.initPorts();
            comPorts.setEventListener();
        } catch (SerialPortException e) {
            error.setText("Cannot init ports. Check, if ports exist");
            error.setName("error");
            Arrays.stream(mainPanel.getComponents())
                    .filter(component -> component.getName() != "error")
                    .forEach(component -> component.setEnabled(false));
        }

        params.setText(comPorts.getStringPortParams());


        buttonSendData.addActionListener(e -> {

            error.setText("");
            String data = dataToSend.getText();

            try {
                if (data.equals("")) {
                    throw new Exception("Data is empty. Try again");
                }

                comPorts.sendData(data);
                String comPortsData = comPorts.getData();
                while (comPortsData == "") {
                    comPortsData = comPorts.getData();
                }

                if (comPortsData == null) throw new NullPointerException("Cannot get data from COM port");

                dataToGet.setText(comPortsData);
                comPorts.setData("");

            } catch (SerialPortException ex) {
                error.setText("Cannot send data to COM port");
            } catch (Exception ex) {
                error.setText(ex.getMessage());
            }

        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }



}
