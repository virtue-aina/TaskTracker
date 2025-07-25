package dev.virtue.tasktracker.domain.dto;

import dev.virtue.tasktracker.domain.entities.TaskPriority;
import dev.virtue.tasktracker.domain.entities.TaskStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime deadline,
        TaskPriority priority,
        TaskStatus status) implements Serializable {
}