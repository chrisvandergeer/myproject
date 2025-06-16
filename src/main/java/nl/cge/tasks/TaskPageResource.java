package nl.cge.tasks;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;

@Path("/tasks")
public class TaskPageResource {

    @Inject
    Template tasks;

    @Inject
    TaskService taskService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getPage() {
        return tasks.data("tasks", taskService.listTasks());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addTask(@FormParam("name") String name,
                                   @FormParam("description") String description,
                                   @FormParam("endDate") String endDate) {
        taskService.addTask(name, description, LocalDate.parse(endDate));
        return tasks.data("tasks", taskService.listTasks());
    }
}
