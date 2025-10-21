package se.mikael.smhi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "smhi-api")
@Path("/point")
public interface SmhiClient {

    @GET
    @Path("/forecast")
    @Produces(MediaType.APPLICATION_JSON)
    String getForecast(@QueryParam("lat") double lat, @QueryParam("lon") double lon);
}
