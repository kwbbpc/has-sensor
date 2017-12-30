package db;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoManager implements DataPipe {

    private String temperatureCollectionName = "temperature";
    private String sensorDbName = "sensors";

    MongoCollection<Document> temperatureCollection;

    public MongoManager(){
        MongoClient client = new MongoClient(new MongoClientURI("mongodb+srv://has:babygirl2017@cluster0-mrmuo.mongodb.net/sensor"));
        this.temperatureCollection = client.getDatabase(sensorDbName).getCollection(temperatureCollectionName);
    }

    public void saveTemperature(TemperatureDao temperature) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        Document tempDoc = Document.parse(mapper.writeValueAsString(temperature));

        this.temperatureCollection.insertOne(tempDoc);

    }



}
