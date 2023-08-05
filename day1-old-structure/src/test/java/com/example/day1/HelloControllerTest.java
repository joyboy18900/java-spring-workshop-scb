package com.example.day1;

import com.example.day1.model.response.HelloResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    // Method injection
    void sayHi() {
        // Act => call to GET /hello
        HelloResponse result = restTemplate.getForObject("/hello", HelloResponse.class);
        // Assert
        assertEquals("Hello Spring", result.getMessage());
    }
}