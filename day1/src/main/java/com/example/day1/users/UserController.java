package com.example.day1.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userServiceV1;

    @Autowired
    private UserCommandService userCommandService;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userResponseList.add(new UserResponse(1,"User A", "LastName A"));
        userResponseList.add(new UserResponse(2,"User B", "lastName B"));
        return userResponseList;
    }

    // Demo of Path Variable
    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable int id) {
        return new UserResponse(id, "Surakiat", "Sangkla");
    }

    // Demo of Request Parameter ?page=1
    @GetMapping("/user")
    public List<UserResponse> getAllUser(@RequestParam(defaultValue = "1") int page) {
        List<UserResponse> userResponseList = new ArrayList<>();
        userResponseList.add(new UserResponse(1, "Mr.test 1", "lastname 1"));
        userResponseList.add(new UserResponse(2, "Mr.test 2", "lastname 2"));
        return userResponseList;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        // 1. Get data from request body
        // 2. Validate input
        // TODO :: 3. call service layer
        // userServiceV1.createUser();
        Integer resultCreateUser = userCommandService.createUser(userRequest);

        // 4. Return response to caller
        UserResponse userResponse = new UserResponse();
        userResponse.setId(resultCreateUser);
        userResponse.setFirstName(userRequest.getFirstName());
        userResponse.setLastName(userRequest.getLastName());

        // Return with success code = 201
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
