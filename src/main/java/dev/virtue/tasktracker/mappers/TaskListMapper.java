package dev.virtue.tasktracker.mappers;

import dev.virtue.tasktracker.domain.dto.TaskListDto;
import dev.virtue.tasktracker.domain.entities.TaskList;
/**
 * Mapper interface for converting between TaskList entities and TaskListDto objects.
 * This interface defines methods to convert a TaskListDto to a TaskList entity and vice versa.
 */
public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
