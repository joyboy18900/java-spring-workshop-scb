package com.example.day1.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommandService {

    @Autowired
    private UserRepository userRepository;

    public UserCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
