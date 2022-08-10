package com.example.servletstest.exception.serviceException.course;

/**
 * Exception will be thrown if course not created.
 */
public class CourseNotCreatedException extends RuntimeException{
    public CourseNotCreatedException() {
        super();
    }

    public CourseNotCreatedException(String message) {
        super(message);
    }

    public CourseNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseNotCreatedException(Throwable cause) {
        super(cause);
    }
}
