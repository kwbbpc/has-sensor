import com.digi.xbee.api.XBeeDevice;

public class ServerMain {


    public static void main(String args[]){

        System.out.println("Server is starting.....");
        XBeeDevice myXBeeDevice = new XBeeDevice("/dev/ttyUSB0", 9600);

        try{
            myXBeeDevice.open();

            myXBeeDevice.addDataListener(new XBeeListener());

            while (true) {
                Thread.sleep(1);
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
