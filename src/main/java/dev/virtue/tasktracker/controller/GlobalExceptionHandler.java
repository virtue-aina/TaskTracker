package dev.virtue.tasktracker.controller;

import dev.virtue.tasktracker.domain.dto.ErrorResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for the application.
 * This class handles exceptions thrown by the application and returns appropriate error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles IllegalArgumentException thrown by the application.
     * @param ex      the exception that was thrown
     * @param request the web request that caused the exception
     * @return a ResponseEntity containing an ErrorResponses object with details of the error
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponses> handleException(
            RuntimeException ex,
            WebRequest request
    ) {

        var errorResponse = new ErrorResponses(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }


}
