package de.share_your_idea.user_management.controller;

import de.share_your_idea.user_management.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static de.share_your_idea.user_management.entity.UserRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserControllerTest {
    @Autowired
    private UserController userController;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldSaveUserEntity() throws Exception {
        /* Given */
        UserEntity userEntity = new UserEntity(1L, "Michael", "testPassword", ROLE_USER, "testAuthorizationToken");
        /* When */
        userController.saveNewUserEntity(userEntity);
        /* Then */
        UserEntity userEntityFromController = userController.findUserEntityByUsername(userEntity.getUsername()).getBody();
        assertThat(userEntityFromController.getUserId()).isEqualTo(userEntity.getUserId());
        assertThat(userEntityFromController.getUsername()).isEqualTo(userEntity.getUsername());
        assertThat(userEntityFromController.getPassword()).isEqualTo(passwordEncoder.encode(userEntity.getPassword()));
        assertThat(userEntityFromController.getUserRole()).isEqualTo(userEntity.getUserRole());
    }
}