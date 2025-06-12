package dev.virtue.tasktracker.service;

import dev.virtue.tasktracker.domain.entities.TaskList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    /**
     * Lists all task lists.
     *
     * @return a list of task lists
     */
    List<TaskList> listTaskLists();
    /**
     * Creates a new task list.
     *
     * @param taskList the task list to create
     * @return the created task list
     */
    TaskList createTaskList(TaskList taskList);
    /**
     * Retrieves a task list by its ID.
     *
     * @param id the ID of the task list to retrieve
     * @return an Optional containing the task list if found, or empty if not found
     */
    Optional<TaskList> getTaskList(UUID id);
    /**
     * Updates an existing task list.
     *
     * @param taskListId the ID of the task list to update
     * @param taskList the updated task list data
     * @return the updated task list
     */
    TaskList updateTaskList(UUID taskListId, TaskList taskList);
    /**
     * Deletes a task list by its ID.
     *
     * @param taskListId the ID of the task list to delete
     */
    void  deleteTaskList(UUID taskListId);
}
