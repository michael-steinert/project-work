package com.example.user_registration.repository;

import com.example.user_registration.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);
}
