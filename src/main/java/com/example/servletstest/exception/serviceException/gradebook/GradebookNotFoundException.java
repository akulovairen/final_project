package com.example.servletstest.exception.serviceException.gradebook;

public class GradebookNotFoundException extends RuntimeException{
    public GradebookNotFoundException() {
        super();
    }

    public GradebookNotFoundException(String message) {
        super(message);
    }

    public GradebookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradebookNotFoundException(Throwable cause) {
        super(cause);
    }
}
