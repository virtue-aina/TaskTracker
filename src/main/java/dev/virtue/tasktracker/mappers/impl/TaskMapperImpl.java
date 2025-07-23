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
