package se.mikael.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import se.mikael.model.SmhiWeatherData;
import se.mikael.service.SmhiService;

@Path("/smhi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SmhiResource {

    @Inject
    SmhiService smhiService;

    @GET
    @Path("/latest")
    public SmhiWeatherData latest() {
        SmhiWeatherData data = smhiService.getLatest();
        if (data == null)
            throw new NotFoundException("No data found. Run /smhi/fetch first.");
        return data;
    }

    @POST
    @Path("/fetch")
    public SmhiWeatherData fetch(@QueryParam("lat") Double lat, @QueryParam("lon") Double lon) {
        double latitude = lat != null ? lat : 58.39;
        double longitude = lon != null ? lon : 13.85;
        return smhiService.fetchAndStore(latitude, longitude);
    }
}
