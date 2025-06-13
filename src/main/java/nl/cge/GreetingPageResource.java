package nl.cge;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingPageResource {

    @Inject
    Template greeting;

    @Inject
    GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getPage() {
        return greeting.data("message", null).data("error", null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance postName(@FormParam("name") String name) {
        try {
            GreetingResponse response = greetingService.greet(name);
            return greeting.data("message", response.message()).data("error", null);
        } catch (IllegalArgumentException e) {
            return greeting.data("message", null).data("error", e.getMessage());
        }
    }
}
