package com.example.servletstest.exception.serviceException.topic;

public class TopicNotFoundException extends RuntimeException{
    public TopicNotFoundException() {
        super();
    }

    public TopicNotFoundException(String message) {
        super(message);
    }

    public TopicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicNotFoundException(Throwable cause) {
        super(cause);
    }
}
