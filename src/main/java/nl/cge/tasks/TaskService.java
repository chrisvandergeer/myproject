package nl.cge.tasks;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class TaskService {
    @Inject
    EntityManager em;

    @Transactional
    public void addTask(String name, String description, LocalDate endDate) {
        em.persist(new Task(name, description, endDate));
    }

    public List<Task> listTasks() {
        return em.createQuery("from Task", Task.class).getResultList();
    }
}
