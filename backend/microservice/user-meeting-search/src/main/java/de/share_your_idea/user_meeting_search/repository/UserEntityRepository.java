package de.share_your_idea.user_meeting_search.repository;

import de.share_your_idea.user_meeting_search.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findUserEntityByUsernameContaining(String username);
}
