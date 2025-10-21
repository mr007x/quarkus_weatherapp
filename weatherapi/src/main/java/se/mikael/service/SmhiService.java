package se.mikael.service;

import io.quarkus.cache.CacheResult;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import se.mikael.model.SmhiWeatherData;
import se.mikael.smhi.SmhiClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SmhiService implements PanacheMongoRepository<SmhiWeatherData> {

    @Inject
    @RestClient
    SmhiClient client;

    @Inject
    ObjectMapper mapper;

    @CacheResult(cacheName = "smhi-latest-cache")
    public SmhiWeatherData fetchAndStore(double lat, double lon) {
        try {
            String json = client.getForecast(lat, lon);
            JsonNode root = mapper.readTree(json);

            SmhiWeatherData data = new SmhiWeatherData();
            data.timestamp = Instant.now();
            data.lat = lat;
            data.lon = lon;
            data.parameters = extractParameters(root);

            persist(data);
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch SMHI data", e);
        }
    }

    private List<SmhiWeatherData.Parameter> extractParameters(JsonNode root) {
        List<SmhiWeatherData.Parameter> params = new ArrayList<>();
        try {
            JsonNode timeSeries = root.get("timeSeries").get(0);
            JsonNode parameters = timeSeries.get("parameters");
            for (JsonNode p : parameters) {
                SmhiWeatherData.Parameter param = new SmhiWeatherData.Parameter();
                param.name = p.get("name").asText();
                param.unit = p.get("unit").asText();
                param.value = p.get("values").get(0).asDouble();
                params.add(param);
            }
        } catch (Exception e) {
            // Felfall: struktur ändrad
        }
        return params;
    }

    public SmhiWeatherData getLatest() {
        return findAll().list()
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }
}
