package com.org.Traini8.ExceptionalHandling;

import java.time.LocalDateTime;

/**
 * This class represents the structure of the error response sent to clients
 * when an exception or an error occurs during API requests. It encapsulates
 * details about the error such as status code, error message, timestamp, and
 * the request path that led to the error.
 */
public class ErrorResponse {

    private LocalDateTime timestamp; // The timestamp when the error occurred.
    private int status; // HTTP status code for the error.
    private String error; // A short description or the name of the error.
    private String message; // Detailed error message for the client.
    private String path; // The API path where the error occurred.

    /**
     * Constructs an ErrorResponse with the specified details.
     *
     * @param timestamp The timestamp at which the error occurred.
     * @param status    The HTTP status code associated with the error.
     * @param error     A brief description of the error type.
     * @param message   A detailed message explaining the error.
     * @param path      The API path that was called when the error occurred.
     */
    public ErrorResponse(final LocalDateTime timestamp, final int status, final String error, final String message,
            final String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Getters

    /**
     * Returns the short error description.
     *
     * @return the error description.
     */
    public String getError() {
        return error;
    }

    /**
     * Returns the detailed error message.
     *
     * @return the error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the API path that was accessed when the error occurred.
     *
     * @return the API path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the HTTP status code of the error.
     *
     * @return the HTTP status code.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Returns the timestamp of when the error occurred.
     *
     * @return the error timestamp.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters

    /**
     * Sets the error description.
     *
     * @param error the new error description.
     */
    public void setError(final String error) {
        this.error = error;
    }

    /**
     * Sets the detailed error message.
     *
     * @param message the new error message.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Sets the API path where the error occurred.
     *
     * @param path the new API path.
     */
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * Sets the HTTP status code for the error.
     *
     * @param status the new HTTP status code.
     */
    public void setStatus(final int status) {
        this.status = status;
    }

    /**
     * Sets the timestamp of when the error occurred.
     *
     * @param timestamp the new timestamp for the error.
     */
    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
