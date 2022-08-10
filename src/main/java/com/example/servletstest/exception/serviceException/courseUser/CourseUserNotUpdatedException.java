package com.example.servletstest.exception.serviceException.courseUser;

public class CourseUserNotUpdatedException extends RuntimeException{
    public CourseUserNotUpdatedException() {
        super();
    }

    public CourseUserNotUpdatedException(String message) {
        super(message);
    }

    public CourseUserNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseUserNotUpdatedException(Throwable cause) {
        super(cause);
    }
}
