package dev.virtue.tasktracker.controller;

import dev.virtue.tasktracker.domain.dto.TaskListDto;
import dev.virtue.tasktracker.mappers.TaskListMapper;
import dev.virtue.tasktracker.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
