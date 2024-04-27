package com.org.Traini8.ExceptionalHandling;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.org.Traini8.ExceptionalHandling.CustomExceptions.TrainingCenterException;
import com.org.Traini8.ExceptionalHandling.CustomExceptions.TrainingCenterRegistrationException;

/**
 * Global exception handler for the application. This class catches and handles
 * exceptions thrown by any controller within the application, ensuring
 * consistent error responses.
 */
public class GlobalExceptionHandler {

    // Logger instance for logging errors
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles generic exceptions that are not caught by more specific handlers.
     * This method captures all exceptions of type Exception and below, providing a
     * generic error response.
     *
     * @param ex      The exception that was caught.
     * @param request The web request during which the exception occurred.
     * @return A ResponseEntity object containing the error details formatted as an
     *         ErrorResponse.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(final Exception ex, final WebRequest request) {
        logger.error("An unexpected error occurred:", ex); // Log the exception details

        // Creating an instance of ErrorResponse with details about the error
        final var errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "An unexpected error occurred",
                request.getDescription(false));

        // Return the ErrorResponse with an appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles exceptions specific to training center operations. Catches exceptions
     * of type TrainingCenterException.
     *
     * @param ex      The TrainingCenterException caught.
     * @param request The web request during which the exception occurred.
     * @return A ResponseEntity object containing the error details.
     */
    @ExceptionHandler(TrainingCenterException.class)
    public ResponseEntity<ErrorResponse> handleTrainingCenterException(final TrainingCenterException ex,
            final WebRequest request) {
        logger.error("Training center exception occurred:", ex); // Log the exception

        // Creating an ErrorResponse instance with specific error details
        final var errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), request.getDescription(false));

        // Return the ErrorResponse with an appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles exceptions related to the registration of training centers. Catches
     * TrainingCenterRegistrationException specifically.
     *
     * @param ex      The TrainingCenterRegistrationException that was caught.
     * @param request The web request during which the exception occurred.
     * @return A ResponseEntity object containing the error details.
     */
    @ExceptionHandler(TrainingCenterRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleTrainingCenterRegistrationException(
            final TrainingCenterRegistrationException ex, final WebRequest request) {
        logger.error("Training center registration exception occurred:", ex); // Log the exception

        // Creating an ErrorResponse instance with specific error details
        final var errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), request.getDescription(false));

        // Return the ErrorResponse with HttpStatus.INTERNAL_SERVER_ERROR
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
