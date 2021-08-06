package com.example.springwebredis.exception;

public class RedisDataNotFoundException extends RuntimeException {
    public RedisDataNotFoundException() {
    }

    public RedisDataNotFoundException(String message) {
        super(message);
    }
}
