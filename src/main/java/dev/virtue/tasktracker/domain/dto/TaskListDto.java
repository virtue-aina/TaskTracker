package dev.virtue.tasktracker.domain.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link dev.virtue.tasktracker.TaskList}
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