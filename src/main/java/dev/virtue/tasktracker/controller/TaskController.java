package dev.virtue.tasktracker.controller;

import dev.virtue.tasktracker.domain.dto.TaskDto;
import dev.virtue.tasktracker.mappers.TaskMapper;
import dev.virtue.tasktracker.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller for managing tasks within a specific task list.
 * Provides endpoints to list tasks and create new tasks.
 */
@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    /**
     * Lists all tasks for a specific task list.
     *
     * @param taskListId the ID of the task list
     * @return a list of tasks in the specified task list
     */
    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
    /**
     * Creates a new task in a specific task list.
     *
     * @param taskListId the ID of the task list
     * @param taskDto    the task data to create
     * @return the created task
     */
    @PostMapping
    public TaskDto createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto){
        var task = taskMapper.fromDto(taskDto);
        var createdTask = taskService.createTask(taskListId,task);
        return taskMapper.toDto(createdTask);
    }

    /**
     * Retrieves a task by its ID within a specific task list.
     * @param taskListId the ID of the task list
     * @param taskId     the ID of the task to retrieve
     * @return an Optional containing the task if found, or empty if not found
     */
    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
        return taskService.getTask(taskListId, taskId)
                .map(taskMapper::toDto);
    }
    /**
     * Updates an existing task within a specific task list.
     * @param taskListId the ID of the task list
     * @param taskId     the ID of the task to update
     * @param taskDto    the updated task data
     * @return the updated task
     */
    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto) {
        var task = taskMapper.fromDto(taskDto);
        var updatedTask = taskService.updateTask(taskListId, taskId, task);
        return taskMapper.toDto(updatedTask);
    }
    /**
     * Deletes a task by its ID within a specific task list.
     * @param taskListId the ID of the task list
     * @param taskId     the ID of the task to delete
     */
    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
        taskService.deleteTask(taskListId, taskId);
    }

}
