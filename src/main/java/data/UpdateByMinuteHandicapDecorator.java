package data;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.DataHandicap;
import db.DataPipe;
import db.HumidityDao;
import db.TemperatureDao;
import org.joda.time.DateTime;

public class UpdateByMinuteHandicapDecorator implements DataPipe {

    private DataPipe pipe;
    private DateTime lastTempUpdate;
    private DateTime lastHumidityUpdate;

    public UpdateByMinuteHandicapDecorator(DataPipe dataPipe){
        this.pipe = dataPipe;
        this.lastTempUpdate = DateTime.now().minusMinutes(1);
        this.lastHumidityUpdate = DateTime.now().minusMinutes(1);
    }

    public DateTime getHandicap() {
        return new DateTime().plusMinutes(1);
    }

    public void saveTemperature(TemperatureDao temperature) throws JsonProcessingException {
        if(DateTime.now().isAfter(this.lastTempUpdate.plusMinutes(1))){
            this.lastTempUpdate = DateTime.now();
            this.pipe.saveTemperature(temperature);
        }
    }

    public void saveHumidity(HumidityDao humidity) throws JsonProcessingException {
        if(DateTime.now().isAfter(this.lastHumidityUpdate.plusMinutes(1))){
            this.lastHumidityUpdate = DateTime.now();
            this.pipe.saveHumidity(humidity);
        }
    }
}
