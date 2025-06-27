package nl.cge.tasks;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

@Path("/api/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskService taskService;

    @GET
    public List<Task> list() {
        return taskService.listTasks();
    }

    public record TaskRequest(String name, String description, String endDate) {}

    @POST
    public List<Task> add(TaskRequest request) {
        taskService.addTask(request.name(), request.description(), LocalDate.parse(request.endDate()));
        return taskService.listTasks();
    }
}
