package dev.virtue.tasktracker.service.impl;

import dev.virtue.tasktracker.domain.entities.Task;
import dev.virtue.tasktracker.repositories.TaskListRepo;
import dev.virtue.tasktracker.service.TaskListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepo taskListRepo;

    public TaskListServiceImpl(TaskListRepo taskListRepo) {
        this.taskListRepo = taskListRepo;
    }

    /**
     * @return
     */
    @Override
    public List<Task> listTaskLists() {
        return List.of();
    }
}
