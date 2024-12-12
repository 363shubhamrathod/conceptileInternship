package com.example.conceptile.exceptions;

import java.util.HashMap;
import java.util.Map;

public class GenericExceptionHandler extends RuntimeException{
    private final Map<String, String> errors = new HashMap<>();

    public GenericExceptionHandler() {
        super();
        errors.put("error","unexpected behaviour occurred");
    }

    public GenericExceptionHandler(String message) {
        super(message);
        errors.put("error",message);
    }

    public GenericExceptionHandler(String message, Throwable cause) {
        super(message, cause);
        errors.put("error",message);
    }

    // Method to add an error to the map
    public void addError(String key, String value) {
        errors.put(key, value);
    }

    // Method to retrieve all errors
    public Map<String, String> getErrors() {
        return errors;
    }
}
