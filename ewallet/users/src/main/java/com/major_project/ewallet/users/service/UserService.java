package com.major_project.ewallet.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.major_project.ewallet.users.entity.UserInfo;
import com.major_project.ewallet.users.exceptions.DuplicateUserException;
import com.major_project.ewallet.users.repository.UserInfoRepository;
import com.major_project.ewallet.users.request.CreateUserRequestDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserInfoRepository repository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static final String USER_CREATED = "USER_CREATED";

    @Transactional(rollbackFor = Exception.class)
    public UserInfo createANewUser(CreateUserRequestDto requestDto){
        UserInfo transientUserInfo = requestDto.toUserInfo();
        Optional<UserInfo> byEmail = repository.findByEmail(transientUserInfo.getEmail());
        if(byEmail.isPresent()) {
            throw new DuplicateUserException();
        }
        return saveOrUpdate(transientUserInfo);
    }

    @SneakyThrows
    public void sendMessage(UserInfo userInfo){
        //String message = objectMapper.writeValueAsString(userInfo);
        kafkaTemplate.send(USER_CREATED,objectMapper.writeValueAsString(userInfo));
    }
    private UserInfo saveOrUpdate(UserInfo userInfo){
        return repository.save(userInfo);
    }
}
