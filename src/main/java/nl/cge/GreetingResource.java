package nl.cge;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalTime;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GreetingResponse greet(GreetingRequest request) {
        String partOfDay = calculatePartOfDay();
        String message = "Hello " + request.name() + ", een hele goede " + partOfDay;
        return new GreetingResponse(message);
    }

    private String calculatePartOfDay() {
        int hour = LocalTime.now().getHour();
        if (hour < 12) {
            return "ochtend";
        }
        if (hour < 18) {
            return "middag";
        }
        return "avond";
    }
}
