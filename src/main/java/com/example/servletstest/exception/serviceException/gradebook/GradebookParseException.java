package com.example.servletstest.exception.serviceException.gradebook;

public class GradebookParseException extends RuntimeException{
    public GradebookParseException() {
        super();
    }

    public GradebookParseException(String message) {
        super(message);
    }

    public GradebookParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public GradebookParseException(Throwable cause) {
        super(cause);
    }
}
