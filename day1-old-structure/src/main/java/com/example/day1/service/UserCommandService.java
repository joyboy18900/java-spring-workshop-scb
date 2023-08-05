package com.example.day1.service;

import com.example.day1.event.EventPublisher;
import com.example.day1.model.exception.DuplicateFirstnameException;
import com.example.day1.entity.UserEntity;
import com.example.day1.repository.UserRepository;
import com.example.day1.model.request.UserRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommandService {

    private UserRepository userRepository;

    @Autowired
    public UserCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    EventPublisher eventPublisher;

    @Transactional
    public void process() {
        UserEntity userEntity = new UserEntity(1, "f1", "l2");
        userRepository.save(userEntity);
        userRepository.deleteAll();
        // Send noti
        eventPublisher.fire("Process done");
        eventPublisher.fire2(1);
    }

    public Integer createUser(UserRequest userRequest) {
        // 1 :: Check firstname duplicate ?
        List<UserEntity> results = userRepository.findByFirstName(userRequest.getFirstName());
        if(results != null && results.size() > 0) {
            // Case 2 :: Firstname duplicated
            throw new DuplicateFirstnameException("");
        }
        // 2. Create new user
        UserEntity userEntity = new UserEntity(1, userRequest.getFirstName(), userRequest.getLastName());
        userRepository.save(userEntity);
        return userEntity.getId();
    }
}
