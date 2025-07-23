package dev.virtue.tasktracker.controller;

import dev.virtue.tasktracker.domain.dto.TaskDto;
import dev.virtue.tasktracker.mappers.TaskMapper;
import dev.virtue.tasktracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        var response = taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto){
        var task = taskMapper.fromDto(taskDto);
        var createdTask = taskService.createTask(taskListId,task);
        var response = taskMapper.toDto(createdTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{task_id}")
    public ResponseEntity<Optional<TaskDto>> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
        var response = taskService.getTask(taskListId, taskId)
                .map(taskMapper::toDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{task_id}")
    public ResponseEntity<TaskDto> updateTask(
           @PathVariable("task_list_id") UUID taskListId,
         @PathVariable("task_id") UUID taskId,
       @RequestBody TaskDto taskDto) {
      var task = taskMapper.fromDto(taskDto);
      var updatedTask = taskService.updateTask(taskListId, taskId, task);
      var response = taskMapper.toDto(updatedTask);
    return ResponseEntity.status(HttpStatus.OK).body(response);
 }

    @DeleteMapping(path = "/{task_id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
      taskService.deleteTask(taskListId, taskId);
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
