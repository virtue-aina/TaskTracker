package dev.virtue.tasktracker.domain.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) implements Serializable {
}