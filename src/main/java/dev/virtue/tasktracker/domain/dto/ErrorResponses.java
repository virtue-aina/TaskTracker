package dev.virtue.tasktracker.domain.dto;

/**
 * Represents an error response with a status code, message, and details.
 * This is used to standardize error responses format across the application.
 */
public record ErrorResponses(
    /**
     * The HTTP status code associated with the error.
     */
    int statusCode,
    /**
     * A brief message describing the error.
     */
    String message,
    /**
     * Additional details about the error, often including the request path or other context.
     */
    String details
) { }
