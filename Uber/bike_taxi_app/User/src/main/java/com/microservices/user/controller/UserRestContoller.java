package com.microservices.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.user.entity.User;
import com.microservices.user.model.DestinationModel;
import com.microservices.user.model.DriverAssignModel;
import com.microservices.user.model.UserRegisterModel;
import com.microservices.user.repository.UserRepository;
import com.microservices.user.service.UserService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/uber")
public class UserRestContoller {

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterModel user){
        userService.saveUser(user);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public  ResponseEntity<String> editUser(@PathVariable Long id, @Valid @RequestBody UserRegisterModel user){
        userService.editUser(id,user);
        return new ResponseEntity<>("Profile updated successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully",HttpStatus.ACCEPTED);
    }

    @SneakyThrows
    @PostMapping("/user/ride")
    @Transactional(timeout = 10)
    public ResponseEntity<DriverAssignModel> requestRide(@Valid @RequestBody DestinationModel destinationModel){
        //Need to assign a driver
        return new ResponseEntity<>(userService.assignDriver(destinationModel),HttpStatus.OK);
    }

    @DeleteMapping("/user/ride/{id}")
    public ResponseEntity<String> cancelRide(@PathVariable Long rideId){
        //Need to cancle the ride
        return new ResponseEntity<>("Ride cancelled successfully",HttpStatus.ACCEPTED);
    }


}
