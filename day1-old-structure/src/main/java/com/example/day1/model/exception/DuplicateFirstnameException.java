package com.example.day1.model.exception;

public class DuplicateFirstnameException extends RuntimeException {
    public DuplicateFirstnameException(String message) {
        super(message);
    }
}
