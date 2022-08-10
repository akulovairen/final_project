package com.example.servletstest.exception.serviceException.gradebook;

public class GradebookNotUpdatedException extends RuntimeException{
    public GradebookNotUpdatedException() {
        super();
    }

    public GradebookNotUpdatedException(String message) {
        super(message);
    }

    public GradebookNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradebookNotUpdatedException(Throwable cause) {
        super(cause);
    }
}
