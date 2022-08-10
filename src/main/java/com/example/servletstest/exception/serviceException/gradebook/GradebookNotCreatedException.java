package com.example.servletstest.exception.serviceException.gradebook;

public class GradebookNotCreatedException extends RuntimeException{
    public GradebookNotCreatedException() {
        super();
    }

    public GradebookNotCreatedException(String message) {
        super(message);
    }

    public GradebookNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradebookNotCreatedException(Throwable cause) {
        super(cause);
    }
}
