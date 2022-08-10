package com.example.servletstest.exception.serviceException.course;

public class CourseNotUpdatedException extends RuntimeException{
    public CourseNotUpdatedException() {
        super();
    }

    public CourseNotUpdatedException(String message) {
        super(message);
    }

    public CourseNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseNotUpdatedException(Throwable cause) {
        super(cause);
    }
}
