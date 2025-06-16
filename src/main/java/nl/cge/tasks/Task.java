package nl.cge.tasks;

import java.time.LocalDate;

public record Task(String name, String description, LocalDate endDate) {}
