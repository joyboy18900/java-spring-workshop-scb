package com.example.day1.users;

import com.example.day1.errors.MyError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class UserControllerAdvice {

//    @ResponseBody
    @ExceptionHandler(DuplicateFirstnameException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<MyError> userNotFoundHandler(DuplicateFirstnameException e) {
        MyError error = new MyError();
        error.setCode("24000");
        error.setDescription("Firstname duplicated");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
