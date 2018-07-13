import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.models.XBeeMessage;
import com.google.protobuf.ByteString;
import data.UpdateByMinuteHandicapDecorator;
import db.mongo.MongoManager;
import weather.handlers.WeatherMessageHandler;
import weather.messages.Weather;

public class XBeeListener implements IDataReceiveListener {

    private WeatherMessageHandler weatherHandler;

    public XBeeListener(WeatherMessageHandler weatherHandler){

        this.weatherHandler = weatherHandler;

        System.out.println("Listening on COM4");
    }

    public void dataReceived(XBeeMessage xBeeMessage) {


        try {

            this.weatherHandler.processMessage(xBeeMessage.getData(), xBeeMessage.getDevice());
            Weather.WeatherMessage msg = Weather.WeatherMessage.parseFrom(ByteString.copyFrom(xBeeMessage.getData()));
            System.out.println("Current temp: " + msg.getTemperatureF());

            //Simple.SimpleMessage m = Simple.SimpleMessage.parseFrom(ByteString.copyFrom(xBeeMessage.getDataString().getBytes()));

        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("Data Received!");
        System.out.println("Device: " + xBeeMessage.getDevice().get16BitAddress());
        System.out.println("Data: " + xBeeMessage.getDataString());
        System.out.println("Data: " + xBeeMessage.getData());
    }
}
