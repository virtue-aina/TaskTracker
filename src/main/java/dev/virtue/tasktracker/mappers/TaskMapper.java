package dev.virtue.tasktracker.mappers;

import dev.virtue.tasktracker.domain.dto.TaskDto;
import dev.virtue.tasktracker.domain.entities.Task;

public interface TaskMapper {
    /**
     * Converts a DTO to an entity
     * @param dto the DTO to convert
     * @return the entity
     */
    Task fromDto(TaskDto dto);
    /**
     * Converts an entity to a DTO
     * @param task the entity to convert
     * @return the DTO
     */
    TaskDto toDto(Task task);
}
