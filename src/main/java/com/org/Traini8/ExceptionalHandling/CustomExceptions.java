package com.org.Traini8.ExceptionalHandling;

/**
 * Contains custom exceptions for the Training Center application. These
 * exceptions are used across the application to handle specific error scenarios
 * related to training center operations.
 */
public class CustomExceptions {

    /**
     * Exception for general training center related errors. This exception is
     * thrown when an operation involving training centers fails, such as data
     * retrieval or processing errors that are not covered by more specific
     * exceptions.
     *
     * The serialVersionUID is a unique identifier for Serializable classes. This is
     * used to verify that the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible with respect to
     * serialization.
     */
    public static class TrainingCenterException extends RuntimeException {
        private static final long serialVersionUID = 5500837691713265307L;

        public TrainingCenterException(final String message) {
            super(message);
        }

        /**
         * Constructs a new TrainingCenterException with the specified detail message.
         *
         * @param message the detail message. The detail message is saved for later
         *                retrieval by the {@link Throwable#getMessage()} method.
         */
        public TrainingCenterException(final String message, final Throwable clause) {
            super(message, clause);
        }
    }

    /**
     * Exception for errors that occur during the registration of a training center.
     * This includes but is not limited to database errors, constraint violations,
     * and data format issues. This exception provides both the error message and
     * the root cause.
     *
     * The serialVersionUID is a unique identifier for Serializable classes. This is
     * used to verify that the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible with respect to
     * serialization.
     */
    public static class TrainingCenterRegistrationException extends RuntimeException {
        private static final long serialVersionUID = -1055176016571864058L;

        /**
         * Constructs a new TrainingCenterRegistrationException with the specified
         * detail message and cause.
         *
         * @param message the detail message (which is saved for later retrieval by the
         *                {@link Throwable#getMessage()} method).
         * @param cause   the cause (which is saved for later retrieval by the
         *                {@link Throwable#getCause()} method). (A null value is
         *                permitted, and indicates that the cause is nonexistent or
         *                unknown.)
         */
        public TrainingCenterRegistrationException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
