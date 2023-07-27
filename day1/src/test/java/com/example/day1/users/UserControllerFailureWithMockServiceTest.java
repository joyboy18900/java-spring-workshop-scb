package com.example.day1.users;

import com.example.day1.errors.MyError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerFailureWithMockServiceTest {

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    private UserCommandService userCommandService;

    @Test
    void createUser_with_duplicate_firstname() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Surakiat");
        userRequest.setLastName("Sangkla");
        // TODO
        when(userCommandService.createUser(userRequest)).thenThrow(DuplicateFirstnameException.class);
        // Send request to api
        ResponseEntity<MyError> result
                = restTemplate.postForEntity("/users", userRequest, MyError.class);
        // Assert
        assertEquals(HttpStatus.CONFLICT.value(), result.getStatusCode().value());
        assertEquals("24000", result.getBody().getCode());
        assertEquals("Firstname duplicated", result.getBody().getDescription());
    }
}