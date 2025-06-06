package dev.virtue.tasktracker.mappers.impl;

import dev.virtue.tasktracker.domain.dto.TaskDto;
import dev.virtue.tasktracker.domain.entities.Task;
import dev.virtue.tasktracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

/**
 * Implementation of the TaskMapper interface that provides methods for converting
 * between Task entities and TaskDto objects.
 */
@Component
public class TaskMapperImpl implements TaskMapper {
    /**
     * Converts a TaskDto object to a Task entity.
     * 
     * @param dto The TaskDto object to convert
     * @return A new Task entity with properties copied from the DTO
     *         (taskList, created, and updated fields are set to null)
     */
    @Override
    public Task fromDto(TaskDto dto) {
        return new Task(
            dto.id(),
            dto.title(),
                    dto.description(),
                    dto.deadline(),
                    dto.priority(),
                    dto.status(),
                null,
                null,
                null);
    }

    /**
     * Converts a Task entity to a TaskDto object.
     * 
     * @param task The Task entity to convert
     * @return A new TaskDto object with properties copied from the Task entity
     *         (taskList, created, and updated fields are not included in the DTO)
     */
    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getDeadline(),
                        task.getPriority(),
                        task.getStatus());
    }
}
