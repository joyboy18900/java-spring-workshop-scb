package com.example.day1.users;

import org.springframework.stereotype.Service;

@Service
public class UserCommandService {

    public Integer createUser(UserRequest userRequest) {
        Integer userId = 0;
        // Case 1 :: Success
        if(!userRequest.getFirstName().equals("Surakiat")) {
            userId = 1;
            return userId;
        }
        // Case 2 :: Firstname duplicated
        throw new DuplicateFirstnameException("");
    }
}
