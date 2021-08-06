package com.example.springwebredis.exception;

public class RedisDataDeleteFailureException extends RuntimeException {
    public RedisDataDeleteFailureException() {
    }

    public RedisDataDeleteFailureException(String message) {
        super(message);
    }
}
