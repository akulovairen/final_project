package com.example.servletstest.exception.serviceException.user;

public class UserNotDeletedException extends RuntimeException{
    public UserNotDeletedException() {
        super();
    }

    public UserNotDeletedException(String message) {
        super(message);
    }

    public UserNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotDeletedException(Throwable cause) {
        super(cause);
    }
}
