package com.pragma.bootcamp.domain.exception;

public class ResourceAlreadyExist extends RuntimeException{
    public ResourceAlreadyExist(String message) {
        super(message);
    }
}
