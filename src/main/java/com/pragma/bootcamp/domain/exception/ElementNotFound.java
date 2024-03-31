package com.pragma.bootcamp.domain.exception;

public class ElementNotFound extends RuntimeException{
    public ElementNotFound(String message) {
        super(message);
    }
}
