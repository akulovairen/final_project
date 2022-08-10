package com.example.servletstest.exception.serviceException.course;

public class CourseNotDeletedException extends RuntimeException{
    public CourseNotDeletedException() {
        super();
    }

    public CourseNotDeletedException(String message) {
        super(message);
    }

    public CourseNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseNotDeletedException(Throwable cause) {
        super(cause);
    }
}
