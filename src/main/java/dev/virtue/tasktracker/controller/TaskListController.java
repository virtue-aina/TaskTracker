package dev.virtue.tasktracker.controller;

import dev.virtue.tasktracker.domain.dto.TaskListDto;
import dev.virtue.tasktracker.mappers.TaskListMapper;
import dev.virtue.tasktracker.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Controller for managing task lists.
 * Provides endpoints to list, create, retrieve, update, and delete task lists.
 */
@RestController
@RequestMapping(path = "api/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @GetMapping
    public ResponseEntity<List<TaskListDto>> listTaskLists() {
        var response = taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
        return ResponseEntity.ok().body(response);
    }
    @PostMapping
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
        var taskList = taskListMapper.fromDto(taskListDto);
        var createdTaskList = taskListService.createTaskList(taskList);
        var response =taskListMapper.toDto(createdTaskList);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

        @GetMapping(path ="/{task_list_id}")
    public ResponseEntity<Optional<TaskListDto>> getTaskListById(@PathVariable("task_list_id") UUID taskListId) {
        var response = taskListService.getTaskList(taskListId)
                .map(taskListMapper::toDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path ="/{task_list_id}")
    public ResponseEntity<TaskListDto> updateTaskList
            (@PathVariable("task_list_id") UUID taskListId,
             @RequestBody TaskListDto taskListDto)
    {
        var taskList = taskListMapper.fromDto(taskListDto);
        var updatedTaskList = taskListService.updateTaskList(taskListId, taskList);
        var response = taskListMapper.toDto(updatedTaskList);
    return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path ="/{task_list_id}")
    public ResponseEntity <Void> deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
