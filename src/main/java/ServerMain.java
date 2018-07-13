import com.digi.xbee.api.XBeeDevice;
import data.UpdateByMinuteHandicapDecorator;
import db.dynamo.DynamoManager;
import db.mongo.MongoManager;
import weather.handlers.WeatherMessageHandler;

public class ServerMain {


    public static void main(String args[]){

        System.out.println("Server is starting.....");
        XBeeDevice myXBeeDevice = new XBeeDevice("/dev/ttyUSB0", 9600);



        try{

            AwsCredentialsLoader creds = new AwsCredentialsLoader("awsAccessKeys.properties");

            WeatherMessageHandler handler =
                    new WeatherMessageHandler(
                                    new DynamoManager(creds.getAccessKey(), creds.getSecretKey()));

            myXBeeDevice.open();

            myXBeeDevice.addDataListener(new XBeeListener(handler));

            while (true) {
                Thread.sleep(1);
            }
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
}
