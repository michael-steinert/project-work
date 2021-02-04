package de.share_your_idea.usermeeting.repository;

import de.share_your_idea.usermeeting.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findUserEntityByUsername(String username);

    @Modifying
    @Transactional(readOnly = false)
    int deleteUserEntityByUsername(String username);
}
