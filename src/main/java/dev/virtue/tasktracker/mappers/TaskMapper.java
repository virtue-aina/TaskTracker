package dev.virtue.tasktracker.mappers;

import dev.virtue.tasktracker.domain.dto.TaskDto;
import dev.virtue.tasktracker.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto dto);
    TaskDto toDto(Task task);
}
