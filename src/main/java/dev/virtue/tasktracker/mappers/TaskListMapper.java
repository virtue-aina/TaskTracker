package dev.virtue.tasktracker.mappers;

import dev.virtue.tasktracker.domain.dto.TaskListDto;
import dev.virtue.tasktracker.domain.entities.TaskList;

public interface TaskListMapper {
    /**
     * Converts a DTO to an entity
     * @param taskListDto the DTO to convert
     * @return the entity
     */
    TaskList fromDto(TaskListDto taskListDto);

    /**
     * Converts an entity to a DTO
     * @param taskList the entity to convert
     * @return the DTO
     */
    TaskListDto toDto(TaskList taskList);
}
