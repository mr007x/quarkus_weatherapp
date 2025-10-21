package se.mikael.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.lang.reflect.Parameter;
import java.time.Instant;
import java.util.List;

@MongoEntity(collection = "smhi_weather_data")
public class SmhiWeatherData {
    public ObjectId id;
    public Instant timestamp;
    public double lat;
    public double lon;
    public List<Parameter> parameters;

    public static class Parameter {
        public String name;
        public String unit;
        public double value;
    }
}
