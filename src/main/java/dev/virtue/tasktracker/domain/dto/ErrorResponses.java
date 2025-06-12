package dev.virtue.tasktracker.domain.dto;

public record ErrorResponses(
    int statusCode,
    String message,
    String details
) { }
