package se.mikael.repository;

import se.mikael.model.WeatherData;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeatherDataRepository implements PanacheMongoRepository<WeatherData> {
}
