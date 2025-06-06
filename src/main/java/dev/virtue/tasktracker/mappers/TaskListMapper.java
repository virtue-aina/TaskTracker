package dev.virtue.tasktracker.mappers;

import dev.virtue.tasktracker.domain.dto.TaskListDto;
import dev.virtue.tasktracker.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList toDto(TaskListDto taskListDto);
    TaskListDto fromDto(TaskList taskList);

}
