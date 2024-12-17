package com.microservices.user.GlobalControllerAdvice;

import com.microservices.user.exceptions.UserExistsException;
import com.microservices.user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestTemplate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserExistsException.class)
    public ResponseEntity<String> userAlreadyExistException(){
        return new ResponseEntity("User already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(){
        return new ResponseEntity("User not found",HttpStatus.BAD_REQUEST);
    }


}
