package dev.virtue.tasktracker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TaskList {
    private UUID id;
    String title;
    String description;
    List<Task> tasks;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
