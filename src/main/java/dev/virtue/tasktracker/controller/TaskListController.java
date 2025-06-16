package dev.virtue.tasktracker.controller;

import dev.virtue.tasktracker.domain.dto.TaskListDto;
import dev.virtue.tasktracker.mappers.TaskListMapper;
import dev.virtue.tasktracker.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;


    /**
     * Lists all task lists.
     * @return a list of all task lists
     */
    @GetMapping
    public List<TaskListDto> listTaskLists() {
      return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }
    /**
     * Creates a new task list.
     * @param taskListDto the task list to create
     * @return the created task list
     */
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        var taskList = taskListMapper.fromDto(taskListDto);
        var createdTaskList = taskListService.createTaskList(taskList);
        return taskListMapper.toDto(createdTaskList);
    }
    /**
     * Retrieves a task list by its ID.
     * @param taskListId the ID of the task list to retrieve
     * @return the task list with the specified ID, or an empty Optional if not found
     */
    @GetMapping(path ="/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskList(taskListId)
                .map(taskListMapper::toDto);
    }
    /**
     * Updates an existing task list.
     * @param taskListId the ID of the task list to update
     * @param taskListDto the updated task list data
     * @return the updated task list
     */
    @PutMapping(path ="/{task_list_id}")
    public TaskListDto updateTaskList
            (@PathVariable("task_list_id") UUID taskListId,
             @RequestBody TaskListDto taskListDto)
    {
        var taskList = taskListMapper.fromDto(taskListDto);
        var updatedTaskList = taskListService.updateTaskList(taskListId, taskList);
        return taskListMapper.toDto(updatedTaskList);
    }
    /**
     * Deletes a task list by its ID.
     * @param taskListId the ID of the task list to delete
     */
    @DeleteMapping(path ="/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
    }
}
