package se.mikael.resource;

import jakarta.ws.rs.*;
import se.mikael.model.WeatherData;
import se.mikael.repository.WeatherDataRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/weather")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeatherDataResource {

    @Inject
    WeatherDataRepository repository;

    @GET
    public List<WeatherData> getAll() {
        return repository.listAll();
    }

    @POST
    public WeatherData add(WeatherData data) {
        repository.persist(data);
        return data;
    }

    @DELETE
    public void deleteAll() {
        repository.deleteAll();
    }
}
