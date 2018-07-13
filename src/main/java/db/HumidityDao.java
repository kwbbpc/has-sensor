package db;

import db.sensor.SensorDao;
import org.joda.time.DateTime;
import weather.messages.Weather;

import java.util.Date;

public class HumidityDao {

    private final Date timestamp;
    private  final float humidity;

    private final SensorDao sensorInfo;


    public HumidityDao(Weather.WeatherMessage message, SensorDao sensor){

        this.timestamp = DateTime.now().toDate();
        this.humidity = message.getHumidity();

        this.sensorInfo = sensor;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public float getHumidity() {
        return humidity;
    }

    public SensorDao getSensorInfo() {
        return sensorInfo;
    }

}
