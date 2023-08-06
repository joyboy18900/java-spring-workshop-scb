package com.example.day1.controller;

import com.example.day1.model.errors.MyError;
import com.example.day1.model.exception.DuplicateFirstnameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(DuplicateFirstnameException.class)
    public ResponseEntity<MyError> userNotFoundHandler(DuplicateFirstnameException e) {
        MyError error = new MyError();
        error.setCode("24000");
        error.setDescription("Firstname duplicated");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
