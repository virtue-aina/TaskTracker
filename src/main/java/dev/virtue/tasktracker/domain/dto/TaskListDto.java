package dev.virtue.tasktracker.domain.dto;

import dev.virtue.tasktracker.domain.entities.TaskList;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link TaskList}
 */
public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) implements Serializable {
}