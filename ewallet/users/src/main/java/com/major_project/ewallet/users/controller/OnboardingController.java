package com.major_project.ewallet.users.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.major_project.ewallet.users.entity.UserInfo;
import com.major_project.ewallet.users.request.CreateUserRequestDto;
import com.major_project.ewallet.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnboardingController {

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(value = "/user" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserRequestDto requestDto) throws JsonProcessingException {
        UserInfo newUser = userService.createANewUser(requestDto);
        userService.sendMessage(newUser);
        return new ResponseEntity<>(objectMapper.writeValueAsString(newUser), HttpStatus.CREATED);
    }
}
