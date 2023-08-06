package com.example.day1.service;

import com.example.day1.model.exception.DuplicateFirstnameException;
import com.example.day1.entity.UserEntity;
import com.example.day1.repository.UserRepository;
import com.example.day1.model.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCommandService {

    private UserRepository userRepository;

    @Autowired
    public UserCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> showAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> showUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Integer createUser(UserRequest userRequest) {
        // 1 :: Check firstname duplicate ?
        List<UserEntity> results = userRepository.findByFirstName(userRequest.getFirstName());
        if(results != null && results.size() > 0) {
            // Case 2 :: Firstname duplicated
            throw new DuplicateFirstnameException("");
        }
        // 2. Create new user
        UserEntity userEntity = new UserEntity(null, userRequest.getFirstName(), userRequest.getLastName());
        userRepository.save(userEntity);
        return userEntity.getId();
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
