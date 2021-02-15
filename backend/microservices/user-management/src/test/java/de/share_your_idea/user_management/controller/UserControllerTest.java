package de.share_your_idea.user_management.controller;

import de.share_your_idea.user_management.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void shouldSaveUserEntity() throws Exception {
        //Given
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Michael");
        userEntity.setPassword("Password");
        userEntity.setUser_role("ROLE_USER");

        //When
        userController.registerUser(userEntity);

        //Then
        UserEntity newUserEntity = userController.fetchUserByUsername(userEntity.getUsername()).getBody();
        assertThat(newUserEntity.getUsername()).isEqualTo("Michael");
        assertThat(newUserEntity.getUser_role()).isEqualTo("ROLE_USER");
    }


}