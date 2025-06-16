package dev.virtue.tasktracker.service.impl;

import dev.virtue.tasktracker.domain.entities.Task;
import dev.virtue.tasktracker.domain.entities.TaskPriority;
import dev.virtue.tasktracker.domain.entities.TaskStatus;
import dev.virtue.tasktracker.repositories.TaskListRepo;
import dev.virtue.tasktracker.repositories.TaskRepo;
import dev.virtue.tasktracker.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
  private final TaskRepo taskRepo;
  private final TaskListRepo taskListRepo;
    /**
     * Lists all tasks for a given task list.
     *
     * @param taskListId the ID of the task list
     * @return a list of tasks in the specified task list
     */
    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepo.findTaskListById(taskListId);
    }
    /** Creates a new task within a specific task list.
        * @param taskListId the ID of the task list
        * @param task       the task to create
        * @return the created task
        */
    @Transactional
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

        var taskList = taskListRepo.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("TaskList with this ID not found"));

        var now =  LocalDateTime.now();
        var newTask = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDeadline(),
                taskPriority,
                taskStatus,
                taskList,
                now,
                now
        );
        return taskRepo.save(newTask);
    }
    /** Retrieves a task by its ID within a specific task list.
        * @param taskListId the ID of the task list
        * @param taskId     the ID of the task to retrieve
        * @return an Optional containing the task if found, or empty if not found
        */
    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepo.findByTaskListIdAndId(taskListId, taskId);
    }
    /** Updates an existing task within a specific task list.
     * @param taskListId the ID of the task list
     * @param taskId     the ID of the task to update
     * @param task       the updated task data
     * @return the updated task
     */
    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task must have ID");
        }
        if (!taskId.equals(task.getId())) {
            throw new IllegalArgumentException("Task ID do not match");
        }
        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task must have a priority");
        }
        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task must have a status");
        }
        var existingTask = taskRepo.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDeadline(task.getDeadline());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepo.save(existingTask);
    }

    /** Deletes a task by its ID within a specific task list.
     * @param taskListId the ID of the task list
     * @param taskId     the ID of the task to delete
     */
    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepo.deleteByTaskListIdAndId(taskListId, taskId);
    }


}
