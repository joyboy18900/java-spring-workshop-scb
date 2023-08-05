package com.example.day1.users;

import com.example.day1.entity.UserEntity;
import com.example.day1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void checkFirstName_duplicated() {
        // Prepare user ::
        UserEntity user1 = new UserEntity(1, "Surakiat", "Sangkla");
        UserEntity user2 = new UserEntity(2, "User2", "Lastname 2");
        userRepository.save(user1);
        userRepository.save(user2);
        // Check Firstname
        List<UserEntity> results = userRepository.findByFirstName("Surakiat");
        // Check result
        assertEquals(1, results.size());
        assertEquals("Surakiat", results.get(0).getFirstName());
    }
}