package dev.virtue.tasktracker.domain.dto;

/**
 * This is used to standardize error responses format across the application.
 */
public record ErrorResponses(
    int statusCode,
    String message,
    String details
) { }
