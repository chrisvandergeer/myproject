package nl.cge.tasks;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public String description;
    public LocalDate endDate;
    public LocalDateTime gereedMoment;

    public Task() {
    }

    public Task(String name, String description, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.endDate = endDate;
    }

    public void markDone() {
        this.gereedMoment = LocalDateTime.now();
    }
}
