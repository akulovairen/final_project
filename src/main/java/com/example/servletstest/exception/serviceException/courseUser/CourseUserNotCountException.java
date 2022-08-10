package com.example.servletstest.exception.serviceException.courseUser;

public class CourseUserNotCountException extends RuntimeException{
    public CourseUserNotCountException() {
        super();
    }

    public CourseUserNotCountException(String message) {
        super(message);
    }

    public CourseUserNotCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseUserNotCountException(Throwable cause) {
        super(cause);
    }
}
