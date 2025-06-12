package dev.virtue.tasktracker.service.impl;

import dev.virtue.tasktracker.domain.entities.Task;
import dev.virtue.tasktracker.domain.entities.TaskPriority;
import dev.virtue.tasktracker.domain.entities.TaskStatus;
import dev.virtue.tasktracker.repositories.TaskRepo;
import dev.virtue.tasktracker.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
  private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }


    /**
     * @param taskListId
     * @return
     */
    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepo.findTaskListById(taskListId);
    }

    /**
     * @param taskListId
     * @param task
     * @return
     */
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task ID already exists");
        }
        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task must have a title");
        }
        var taskPriority = Optional.ofNullable(task.getPriority()).
                orElse(TaskPriority.LOW);

        var taskStatus = Optional.ofNullable(task.getStatus()).
                orElse(TaskStatus.OPEN);
        return null;
    }


}
