package dev.virtue.tasktracker.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link Task}
 */
public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime deadline,
        TaskPriority priority,
        TaskStatus status) implements Serializable {
}