package db;

import com.digi.xbee.api.AbstractXBeeDevice;
import db.sensor.SensorDao;
import org.joda.time.DateTime;
import weather.messages.Weather;

import java.util.Date;

public class TemperatureDao {

    private final Date timestamp;
    private final float temperature;

    private final SensorDao sensorInfo;


    public TemperatureDao(Weather.WeatherMessage message, SensorDao sensor){

        this.timestamp = DateTime.now().toDate();
        this.temperature = message.getTemperatureF();

        this.sensorInfo = sensor;
    }

    public Date getTimestamp() {
        return timestamp;
    }


    public float getTemperature() {
        return temperature;
    }


    public SensorDao getSensorInfo() {
        return sensorInfo;
    }

}
