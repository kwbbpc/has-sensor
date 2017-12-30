package data;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.DataHandicap;
import db.DataPipe;
import db.TemperatureDao;
import org.joda.time.DateTime;

public class UpdateByMinuteHandicapDecorator implements DataPipe {

    private DataPipe pipe;
    private DateTime lastUpdate;

    public UpdateByMinuteHandicapDecorator(DataPipe dataPipe){
        this.pipe = dataPipe;
        this.lastUpdate = DateTime.now().minusMinutes(1);
    }

    public DateTime getHandicap() {
        return new DateTime().plusMinutes(1);
    }

    public void saveTemperature(TemperatureDao temperature) throws JsonProcessingException {
        if(DateTime.now().isAfter(this.lastUpdate.plusMinutes(1))){
            this.lastUpdate = DateTime.now();
            this.pipe.saveTemperature(temperature);
        }
    }
}
