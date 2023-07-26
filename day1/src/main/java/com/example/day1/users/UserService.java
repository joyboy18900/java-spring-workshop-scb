package com.example.day1.users;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

interface UserService {
    void createUser();
}

@Service
class UserServiceV1 implements UserService {

    @Override
    public void createUser() {

        System.out.println("UserServiceV1");
    }
}

@Service
class UserServiceV2 implements UserService {

    @Override
    public void createUser() {

        System.out.println("UserServiceV2");
    }
}
