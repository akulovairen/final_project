package com.example.servletstest.exception.serviceException.user;

public class UserNotUnblockedException extends RuntimeException {
    public UserNotUnblockedException() {
        super();
    }

    public UserNotUnblockedException(String message) {
        super(message);
    }

    public UserNotUnblockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotUnblockedException(Throwable cause) {
        super(cause);
    }
}
