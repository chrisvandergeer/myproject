package nl.cge.tasks;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public synchronized void addTask(String name, String description, LocalDate endDate) {
        tasks.add(new Task(name, description, endDate));
    }

    public List<Task> listTasks() {
        return Collections.unmodifiableList(tasks);
    }
}
