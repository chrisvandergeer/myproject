package nl.cge.greeting;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/greeting")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @GET
    public GreetingResponse get(@QueryParam("name") String name) {
        try {
            return greetingService.greet(name);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public record GreetingRequest(String name) {}

    @POST
    public GreetingResponse post(GreetingRequest request) {
        try {
            return greetingService.greet(request.name());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
