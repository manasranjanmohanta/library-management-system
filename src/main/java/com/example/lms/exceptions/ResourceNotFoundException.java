package com.example.lms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private String stringFieldValue;
    private long longFieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long longFieldValue) {
        super(String.format("%s is not found with %s : %s", resourceName, fieldName, longFieldValue));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.longFieldValue = longFieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String stringFieldValue) {
        super(String.format("%s is not found with %s : %s", resourceName, fieldName, stringFieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.stringFieldValue = stringFieldValue;
    }
}
