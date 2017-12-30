package weather.handlers;

import com.digi.xbee.api.AbstractXBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.mongodb.Mongo;
import core.SensorHandler;
import db.DataPipe;
import db.MongoManager;
import db.TemperatureDao;
import db.sensor.SensorDao;
import org.joda.time.DateTime;
import weather.messages.Weather;

public class WeatherMessageHandler implements SensorHandler {

    private DataPipe manager;
    private DateTime lastUpdated;

    public WeatherMessageHandler(DataPipe pipe) {
        this.manager = pipe;
        this.lastUpdated = new DateTime();
    }

    public void processMessage(byte[] data, AbstractXBeeDevice device) {

        try {

            //parse the weather message
            Weather.WeatherMessage msg = Weather.WeatherMessage.parseFrom(ByteString.copyFrom(data));

            //format the sensor info
            SensorDao  sensor = new SensorDao(device);
            TemperatureDao temp = new TemperatureDao(msg, sensor);

            this.manager.saveTemperature(temp);


        }
        catch(InvalidProtocolBufferException e){

            System.err.println("Error processing temperature message: " + e.getMessage());

        }catch(XBeeException e){

            System.err.print("Error getting xbee device info: " + e.getMessage());
            System.err.println("Device: " + device.get64BitAddress().toString());

        }catch(JsonProcessingException e){

            System.err.println("Error saving temperature data: " + e.getMessage());
            System.err.println("Temperature data: " + data);

        }

    }


}
