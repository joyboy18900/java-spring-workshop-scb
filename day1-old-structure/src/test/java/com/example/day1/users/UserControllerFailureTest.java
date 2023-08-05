package com.example.day1.users;

import com.example.day1.entity.UserEntity;
import com.example.day1.model.errors.MyError;
import com.example.day1.model.request.UserRequest;
import com.example.day1.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerFailureTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void initDateForTest() {
        // Prepare user ::
        userRepository.deleteAll();
        UserEntity user1 = new UserEntity(1, "Surakiat", "Sangkla");
        UserEntity user2 = new UserEntity(2, "User 2", "Lastname 2");
        UserEntity user3 = new UserEntity(3, "User 3", "Lastname 3");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @AfterEach
    public void clearData() {
        userRepository.deleteAll();
    }

//    @Test
//    void createUserWithFail() {
//        UserRequest userRequest = new UserRequest();
//        userRequest.setFirstName("Surakiat");
//        userRequest.setLastName("Sangkla");
//        ResponseEntity<MyError> result = restTemplate.postForEntity("/users", userRequest, MyError.class);
//
//        // Assert
//        assertEquals(HttpStatus.CONFLICT.value(), result.getStatusCode().value());
//        assertEquals("24000", result.getBody().getCode());
//        assertEquals("Firstname duplicated", result.getBody().getDescription());
//    }

    @Test
    void createUserWithDuplicateFirstname() {
        // Send request to api
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Surakiat");
        userRequest.setLastName("Sangkla");
        ResponseEntity<MyError> result
                = restTemplate.postForEntity("/users", userRequest, MyError.class);
        // Assert
        assertEquals(HttpStatus.CONFLICT.value(), result.getStatusCode().value());
        assertEquals("24000", result.getBody().getCode());
        assertEquals("Firstname duplicated", result.getBody().getDescription());
    }
}