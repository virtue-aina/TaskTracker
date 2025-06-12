package dev.virtue.tasktracker.service.impl;

import dev.virtue.tasktracker.domain.entities.TaskList;
import dev.virtue.tasktracker.repositories.TaskListRepo;
import dev.virtue.tasktracker.service.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepo taskListRepo;

    public TaskListServiceImpl(TaskListRepo taskListRepo) {
        this.taskListRepo = taskListRepo;
    }

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
}
