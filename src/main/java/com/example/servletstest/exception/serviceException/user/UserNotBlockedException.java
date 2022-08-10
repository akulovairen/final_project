package com.example.servletstest.exception.serviceException.user;

public class UserNotBlockedException extends RuntimeException{
    public UserNotBlockedException() {
        super();
    }

    public UserNotBlockedException(String message) {
        super(message);
    }

    public UserNotBlockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotBlockedException(Throwable cause) {
        super(cause);
    }
}
