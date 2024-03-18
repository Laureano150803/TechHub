package com.pragma.bootcamp.domain.exception;

public class NameExceedsMaxLengthException extends RuntimeException {
    public NameExceedsMaxLengthException(String message){
        super(message);
    }
}
