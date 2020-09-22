import jssc.SerialPort;
import jssc.SerialPortException;


import java.util.Arrays;

public class Ports {

    final static int baudRate = SerialPort.BAUDRATE_9600;
    final static int dataBits = SerialPort.DATABITS_8;
    final static int stopBits = SerialPort.STOPBITS_1;
    final static int parity = SerialPort.PARITY_NONE;
    final static int mask=SerialPort.MASK_RXCHAR;

    private static SerialPort receiver;
    private static SerialPort sender;

    private String data="";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public Ports(String senderName, String receiverName) {
        sender = new SerialPort(senderName);
        receiver = new SerialPort(receiverName);
    }

    public void initPorts() throws SerialPortException {
        for (SerialPort port : Arrays.asList(sender, receiver)) {
            port.openPort();
            port.setParams(baudRate, dataBits, stopBits, parity);
            port.setEventsMask(mask);
        }
    }

    public void setEventListener() throws SerialPortException {
        receiver.addEventListener((serialPortEvent -> {
            if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
                try {
                    this.data = receiver.readString(serialPortEvent.getEventValue());
                } catch (SerialPortException e) {
                    data=null;
                }
            }
        }));
    }

    public void sendData(String data) throws SerialPortException {
        sender.writeString(data);
    }

    public String getStringPortParams() {
        String info= "Baudrate: " + baudRate + " bauds\n"
                + "Data bits: " + dataBits + "\n"
                + "Stop bits: " + stopBits + "\n"
                +"Parity: ";

        switch(parity) {
            case SerialPort.PARITY_NONE:
                info+="No";
                break;
            case SerialPort.PARITY_ODD:
                info+="odd";
                break;
            case SerialPort.PARITY_EVEN:
                info+="even";
                break;
        }

        return info;
    }


}
