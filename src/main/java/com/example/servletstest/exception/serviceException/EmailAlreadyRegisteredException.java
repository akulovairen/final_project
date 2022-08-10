package com.example.servletstest.exception.serviceException;

public class EmailAlreadyRegisteredException extends RuntimeException{
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }

    public EmailAlreadyRegisteredException(Exception ex) {
        super(ex);
    }
}
