package com.example.servletstest.exception.serviceException.topic;

public class TopicNotUpdatedException extends RuntimeException{
    public TopicNotUpdatedException() {
        super();
    }

    public TopicNotUpdatedException(String message) {
        super(message);
    }

    public TopicNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicNotUpdatedException(Throwable cause) {
        super(cause);
    }
}
