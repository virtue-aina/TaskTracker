package dev.virtue.tasktracker.controller;

import dev.virtue.tasktracker.domain.dto.TaskListDto;
import dev.virtue.tasktracker.mappers.TaskListMapper;
import dev.virtue.tasktracker.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
      return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        var taskList = taskListMapper.fromDto(taskListDto);
        var createdTaskList = taskListService.createTaskList(taskList);
        return taskListMapper.toDto(createdTaskList);
    }
    @GetMapping(path ="/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskList(taskListId)
                .map(taskListMapper::toDto);
    }

    @PutMapping(path ="/{task_list_id}")
    public TaskListDto updateTaskList
            (@PathVariable("task_list_id") UUID taskListId,
             @RequestBody TaskListDto taskListDto)
    {
        var taskList = taskListMapper.fromDto(taskListDto);
        var updatedTaskList = taskListService.updateTaskList(taskListId, taskList);
        return taskListMapper.toDto(updatedTaskList);
    }

   // @DeleteMapping(path ="/{task_list_id}")
}
