package com.example.day1.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCommandServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void createUser_success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Surakiat");
        userRequest.setLastName("Sangkla");

        UserCommandService userCommandService = new UserCommandService(userRepository);
        userCommandService.createUser(userRequest);
    }

    @Test
    void createUser_failure_duplicate_firstname() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Surakiat");
        userRequest.setLastName("Sangkla");

        List<UserEntity> results = new ArrayList<>();
        results.add(new UserEntity());
        when(userRepository.findByFirstName("Surakiat")).thenReturn(results);

        UserCommandService service = new UserCommandService(userRepository);
        Exception exception = assertThrows(
                DuplicateFirstnameException.class, () -> {
                    service.createUser(userRequest);
                });
        assertEquals("", exception.getMessage());
    }

    @Test
    void createUser_failure_duplicate_firstname_with_spy_v1(){
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Surakiat");
        userRequest.setLastName("Sangkla");

        List<UserEntity> results = new ArrayList<>();
        results.add(new UserEntity());
        when(userRepository.findByFirstName("Surakiat")).thenReturn(results);

        UserCommandService service = new UserCommandService(userRepository);
        try {
            service.createUser(userRequest);
        } catch (Exception e) {}
        // Assert
        verify(userRepository, times(1)).findByFirstName("Surakiat");
    }

    @InjectMocks
    UserCommandService userCommandServiceV2;

    @Test
    void createUser_failure_duplicate_firstname_with_spy_v2(){
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Surakiat");
        userRequest.setLastName("Sangkla");

        List<UserEntity> results = new ArrayList<>();
        results.add(new UserEntity());
        when(userRepository.findByFirstName("Surakiat")).thenReturn(results);

        try {
            userCommandServiceV2.createUser(userRequest);
        } catch (Exception e) {}
        // Assert
        verify(userRepository, times(1)).findByFirstName("Surakiat");
    }
}