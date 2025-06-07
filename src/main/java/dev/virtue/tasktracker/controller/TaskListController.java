package dev.virtue.tasktracker.controller;

import dev.virtue.tasktracker.domain.dto.TaskListDto;
import dev.virtue.tasktracker.mappers.TaskListMapper;
import dev.virtue.tasktracker.service.TaskListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }


    @GetMapping
    public List<TaskListDto> listTaskLists() {
        // This method should return a list of TaskListDto objects.
        // For now, we will return an empty list as a placeholder.
        return List.of();
    }
}
