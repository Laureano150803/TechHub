package com.pragma.bootcamp.domain.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String FIELD_NAME_EMPTY_MESSAGE = "Field 'name' cannot be empty";
    public static final String FIELD_DESCRIPTION_EMPTY_MESSAGE = "Field 'description' cannot be empty";
}
