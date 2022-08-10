package com.example.servletstest.exception.serviceException.topic;

public class TopicNotCreatedException extends RuntimeException{
    public TopicNotCreatedException() {
        super();
    }

    public TopicNotCreatedException(String message) {
        super(message);
    }

    public TopicNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicNotCreatedException(Throwable cause) {
        super(cause);
    }
}
