package com.microservices.user.service;

import com.microservices.user.entity.User;
import com.microservices.user.exceptions.UserExistsException;
import com.microservices.user.exceptions.UserNotFoundException;
import com.microservices.user.model.DestinationModel;
import com.microservices.user.model.DriverAssignModel;
import com.microservices.user.model.UserRegisterModel;
import com.microservices.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;
    @Override
    public void saveUser(UserRegisterModel userModel) {
            User user = buildUser(userModel);
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if(existingUser.isPresent()){
                throw new UserExistsException();
            }

             saveOrUpdateUser(user);
    }



    @Override
    public void editUser(Long id, UserRegisterModel userModel) {
        User user = buildUser(userModel);
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isEmpty()){
            throw new UserNotFoundException();
        }
        user.setId(id);
        saveOrUpdateUser(user);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get();
    }

    /**
     * @param id
     */
    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        deleteUserById(id);
    }

    /**
     * @param destinationModel
     * @return
     */
    @Override
    public DriverAssignModel assignDriver(DestinationModel destinationModel) {
        //Kafka usage
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private User saveOrUpdateUser(User user) {
        return  (User) userRepository.save(user);
    }


    private User buildUser(UserRegisterModel user){
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
