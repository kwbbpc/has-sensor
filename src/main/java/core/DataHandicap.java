package core;

import com.fasterxml.jackson.core.JsonProcessingException;
import db.DataPipe;
import db.MongoManager;
import db.TemperatureDao;
import org.joda.time.DateTime;

public interface DataHandicap {

    DateTime getHandicap();

}
