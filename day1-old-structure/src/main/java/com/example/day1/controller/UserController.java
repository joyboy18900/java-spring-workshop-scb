package com.example.day1.controller;

import com.example.day1.entity.UserEntity;
import com.example.day1.model.request.UserRequest;
import com.example.day1.model.response.UserResponse;
import com.example.day1.service.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserCommandService userCommandService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserEntity> users = userCommandService.showAllUsers();
        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserResponse> userResponseList = new ArrayList<>();
        for (UserEntity userEntity : users) {
            UserResponse userResponse = new UserResponse();

            userResponse.setId(userEntity.getId());
            userResponse.setFirstName(userEntity.getFirstName());
            userResponse.setLastName(userEntity.getLastName());

            userResponseList.add(userResponse);
        }

//        userResponseList = users.stream()
//                .map(userEntity -> {
//                    UserResponse userResponse = new UserResponse();
//                    userResponse.setId(userEntity.getId());
//                    userResponse.setFirstName(userEntity.getFirstName());
//                    userResponse.setLastName(userEntity.getLastName());
//                    return userResponse;
//                })
//                .collect(Collectors.toList());

        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        Optional<UserEntity> user = userCommandService.showUserById(id);
        if (user.isPresent()) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.get().getId());
            userResponse.setFirstName(user.get().getFirstName());
            userResponse.setLastName(user.get().getLastName());

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        // 1. Get data from request body
        // 2. Validate input
        // TODO :: 3. call service layer
        Integer resultCreateUser = userCommandService.createUser(userRequest);

        // 4. Return response to caller
        UserResponse userResponse = new UserResponse();
        userResponse.setId(resultCreateUser);
        userResponse.setFirstName(userRequest.getFirstName());
        userResponse.setLastName(userRequest.getLastName());

        // Return with success code = 201
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") int id, @RequestBody UserEntity user) {
        Optional<UserEntity> userData = userCommandService.showUserById(id);
        if (userData.isPresent()) {
            UserEntity userDataTemp = userData.get();
            userDataTemp.setFirstName(user.getFirstName());
            userDataTemp.setLastName(user.getLastName());

            // Return response to caller
            UserEntity resultUser = userCommandService.saveUser(userDataTemp);
            UserResponse userResponse = new UserResponse();
            userResponse.setId(resultUser.getId());
            userResponse.setFirstName(resultUser.getFirstName());
            userResponse.setLastName(resultUser.getLastName());

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        try {
            userCommandService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete the user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
