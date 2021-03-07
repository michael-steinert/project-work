package de.share_your_idea.user_meeting.repository;

import de.share_your_idea.user_meeting.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static de.share_your_idea.user_meeting.entity.UserRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/* To trigger the Annotations from UserEntity the following Property is necessary */
/* For Example @Column(nullable = false) */
@DataJpaTest(properties = "spring.jpa.properties.javax.persistence.validation.mode=none")
class UserEntityRepositoryTest {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    void itShouldFindUserEntityByUsername() {
        /* Given */
        UserEntity userEntity = new UserEntity(1L, "Michael", "testPassword", ROLE_USER, "testAuthorizationToken", null);
        /* When */
        userEntityRepository.save(userEntity);
        /* Then */
        Optional<UserEntity> userEntityOptional = userEntityRepository.findUserEntityByUsername(userEntity.getUsername());
        assertThat(userEntityOptional).isPresent().hasValueSatisfying(userEntityFromRepository -> {
            assertThat(userEntityFromRepository.getUserId()).isEqualTo(userEntity.getUserId());
            assertThat(userEntityFromRepository.getUsername()).isEqualTo(userEntity.getUsername());
            assertThat(userEntityFromRepository.getUserRole()).isEqualTo(userEntity.getUserRole());
            assertThat(userEntityFromRepository.getAuthorizationToken()).isEqualTo(userEntity.getAuthorizationToken());
        });
    }

    @Test
    void itShouldDeleteUserEntityByUsername() {
        /* Given */
        /* When */
        /* Then */
    }
}