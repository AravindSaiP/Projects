package com.major_project.ewallet.users.advice;

import com.major_project.ewallet.users.exceptions.DuplicateUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {DuplicateUserException.class})
    public ResponseEntity<String> generateUserNotFoundResponse(){
        return new ResponseEntity<>("user already exists", HttpStatus.BAD_REQUEST);
    }
}
