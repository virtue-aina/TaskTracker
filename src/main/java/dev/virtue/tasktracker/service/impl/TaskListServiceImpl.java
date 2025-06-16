package dev.virtue.tasktracker.service.impl;

import dev.virtue.tasktracker.domain.entities.TaskList;
import dev.virtue.tasktracker.repositories.TaskListRepo;
import dev.virtue.tasktracker.service.TaskListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepo taskListRepo;
    /**
     * Lists all task lists.
     *
     * @return a list of all task lists
     */
    @Override
    public List<TaskList> listTaskLists() {

        return taskListRepo.findAll();

    }

    /**
     * Creates a new task list.
     *
     * @param taskList the task list to create
     * @return the created task list
     */
    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (taskList.getId() != null) {
            throw new IllegalArgumentException("TaskList ID already exists");
        }
        if (null == taskList.getTitle()) {
            throw new IllegalArgumentException("TaskList must have a title");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepo.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    /**
     * Retrieves a task list by its ID.
     *
     * @param id the ID of the task list
     * @return an Optional containing the task list if found, or empty if not found
     */
    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepo.findById(id);
    }
  /**
     * Updates an existing task list.
     *
     * @param taskListId the ID of the task list to update
     * @param taskList   the updated task list
     * @return the updated task list
     */
    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (null == taskList.getId()){
            throw new IllegalArgumentException("TaskList must have an ID");
        }

        if (!taskListId.equals(taskList.getId())) {
            throw new IllegalArgumentException("Attempting to change TaskList ID, not permitted");
        }

        var existngTaskList = taskListRepo.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("TaskList not found"));
        existngTaskList.setTitle(taskList.getTitle());
        existngTaskList.setDescription(taskList.getDescription());
        existngTaskList.setUpdatedAt(LocalDateTime.now());
        return taskListRepo.save(existngTaskList);

    }

    /**
        * Deletes a task list by its ID.
        * @param taskListId the ID of the task list to delete
        */
    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepo.deleteById(taskListId);
    }
}
