package db;

import com.digi.xbee.api.AbstractXBeeDevice;
import db.sensor.SensorDao;
import org.joda.time.DateTime;
import weather.messages.Weather;

import java.util.Date;

public class TemperatureDao {

    private Date timestamp;
    private float temperature;

    private SensorDao sensorInfo;

    public TemperatureDao() {
    }

    public TemperatureDao(Weather.WeatherMessage message, SensorDao sensor){

        this.timestamp = DateTime.now().toDate();
        this.temperature = message.getTemperatureF();

        this.sensorInfo = sensor;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public SensorDao getSensorInfo() {
        return sensorInfo;
    }

    public void setSensorInfo(SensorDao sensorInfo) {
        this.sensorInfo = sensorInfo;
    }
}
