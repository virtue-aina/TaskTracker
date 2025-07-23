package dev.virtue.tasktracker.mappers;

import dev.virtue.tasktracker.domain.dto.TaskDto;
import dev.virtue.tasktracker.domain.entities.Task;
/**
 * Mapper interface for converting between Task entities and TaskDto objects.
 * This interface defines methods to convert a TaskDto to a Task entity and vice versa.
 */
public interface TaskMapper {
    Task fromDto(TaskDto dto);
    TaskDto toDto(Task task);
}
