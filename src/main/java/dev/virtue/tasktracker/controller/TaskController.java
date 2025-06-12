package dev.virtue.tasktracker.controller;

import dev.virtue.tasktracker.domain.dto.TaskDto;
import dev.virtue.tasktracker.mappers.TaskMapper;
import dev.virtue.tasktracker.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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

//    @PostMapping
//    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDto taskDto) {
//        var task = taskMapper.fromDto(taskDto);
//        task.setTaskListId(taskListId); // Set the task list ID
//        var createdTask = taskService.createTask(task);
//        return taskMapper.toDto(createdTask);
//    }

}
