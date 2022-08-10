package com.example.servletstest.util.sessionManager;

public class SessionManagerException extends RuntimeException {
    public SessionManagerException(String msg) {
        super(msg);
    }
    public SessionManagerException(Exception ex) {
        super(ex);
    }

    public SessionManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
