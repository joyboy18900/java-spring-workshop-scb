package com.example.day1.repository;

import com.example.day1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByFirstName(String firstName);
}
