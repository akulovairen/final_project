package com.example.servletstest.exception.serviceException.user;

public class UserNotUpdatedException extends RuntimeException{
    public UserNotUpdatedException() {
        super();
    }

    public UserNotUpdatedException(String message) {
        super(message);
    }

    public UserNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotUpdatedException(Throwable cause) {
        super(cause);
    }
}
