package de.share_your_idea.user_management.repository;

import de.share_your_idea.user_management.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;

import java.util.Optional;

import static de.share_your_idea.user_management.entity.UserRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;

/* Unit-Test for UserEntityRepository */
/* To trigger the Annotations from UserEntity the following Property is necessary */
/* For Example @Column(nullable = false) */
@DataJpaTest(properties = "spring.jpa.properties.javax.persistence.validation.mode=none")
@PropertySource("classpath:application.yml")
@PropertySource("classpath:bootstrap.yml")
class UserEntityRepositoryTest {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    void itShouldFindUserEntityByUsername() {
        /* Given */
        UserEntity userEntity = new UserEntity(1L, "Michael", "testPassword", ROLE_USER, "testAuthorizationToken");
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
        UserEntity userEntity = new UserEntity(1L, "Michael", "testPassword", ROLE_USER, "testAuthorizationToken");
        /* When */
        userEntityRepository.save(userEntity);
        /* Then */
        int result = userEntityRepository.deleteUserEntityByUsername(userEntity.getUsername());
        assertThat(result).isEqualTo(1);
        Optional<UserEntity> userEntityOptional = userEntityRepository.findById(userEntity.getUserId());
        assertThat(userEntityOptional).isEmpty();
    }
}