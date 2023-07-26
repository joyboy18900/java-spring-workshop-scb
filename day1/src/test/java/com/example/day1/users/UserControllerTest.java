package com.example.day1.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void createUserWithSuccess() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("newFirstname");
        userRequest.setLastName("newLastname");
        ResponseEntity<UserResponse> result = restTemplate.postForEntity("/users", userRequest, UserResponse.class);

        // Assert
        assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
        assertEquals("newFirstname", userRequest.getFirstName());
        assertEquals("newLastname", userRequest.getLastName());
    }

    @Test
    void createUserWithFail() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Surakiat");
        userRequest.setLastName("Sangkla");
        ResponseEntity<MyError> result = restTemplate.postForEntity("/users", userRequest, MyError.class);

        // Assert
        assertEquals(HttpStatus.CONFLICT.value(), result.getStatusCode().value());
        assertEquals("24000", result.getBody().getCode());
        assertEquals("Firstname duplicated", result.getBody().getDescription());
    }
}