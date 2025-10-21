package se.mikael.model;

import org.bson.types.ObjectId;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;

@MongoEntity(collection = "weatherdata")
public class WeatherData extends PanacheMongoEntityBase {

    public ObjectId id;
    public String source;
    public String city;
    public double temperature;
    public double humidity;
    public long timestamp;

    public WeatherData() {}

    public WeatherData(String source, String city, double temperature, double humidity, long timestamp) {
        this.source = source;
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }
}
