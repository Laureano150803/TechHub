package com.pragma.bootcamp.domain.exception;

public class DescriptionExceedsMaxLengthException extends RuntimeException{
    public DescriptionExceedsMaxLengthException(String message) {
        super(message);
    }
}
