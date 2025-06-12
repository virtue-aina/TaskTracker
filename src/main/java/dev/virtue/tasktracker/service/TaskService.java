package dev.virtue.tasktracker.service;

import dev.virtue.tasktracker.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId, Task task);
    // Optional<Task> getTask(UUID id);
    // Task updateTask(UUID taskId, Task task);
    // void deleteTask(UUID taskId);
}
