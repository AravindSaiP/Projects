package com.microservices.user.service;

import com.microservices.user.entity.User;
import com.microservices.user.model.DestinationModel;
import com.microservices.user.model.DriverAssignModel;
import com.microservices.user.model.UserRegisterModel;
import org.springframework.stereotype.Service;


public interface UserService {
    void saveUser(UserRegisterModel user);

    void editUser(Long id, UserRegisterModel user);

    User getUserById(Long id);

    void deleteUser(Long id);

    DriverAssignModel assignDriver(DestinationModel destinationModel);
}
