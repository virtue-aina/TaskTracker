package dev.virtue.tasktracker.service;

import dev.virtue.tasktracker.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
List<TaskList> listTaskLists();
TaskList createTaskList(TaskList taskList);
}
