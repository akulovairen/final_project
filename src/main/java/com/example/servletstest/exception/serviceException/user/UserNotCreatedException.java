package com.example.servletstest.exception.serviceException.user;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException() {
        super();
    }

    public UserNotCreatedException(String message) {
        super(message);
    }

    public UserNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotCreatedException(Throwable cause) {
        super(cause);
    }
}
