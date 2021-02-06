package de.share_your_idea.usermeetingsearch.repository;

import de.share_your_idea.usermeetingsearch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findUserEntityByUsernameContaining(String username);
}
