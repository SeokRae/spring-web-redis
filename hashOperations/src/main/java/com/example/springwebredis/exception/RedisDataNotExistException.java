package com.example.springwebredis.exception;

public class RedisDataNotExistException extends RuntimeException {
    public RedisDataNotExistException() {
    }

    public RedisDataNotExistException(String message) {
        super(message);
    }
}
