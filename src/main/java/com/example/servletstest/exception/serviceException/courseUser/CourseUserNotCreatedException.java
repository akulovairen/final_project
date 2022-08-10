package com.example.servletstest.exception.serviceException.courseUser;

public class CourseUserNotCreatedException extends RuntimeException{
    public CourseUserNotCreatedException() {
        super();
    }

    public CourseUserNotCreatedException(String message) {
        super(message);
    }

    public CourseUserNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseUserNotCreatedException(Throwable cause) {
        super(cause);
    }
}
