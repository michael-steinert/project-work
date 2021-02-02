package de.share_your_idea.user_management.repository;

import de.share_your_idea.user_management.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);
}
