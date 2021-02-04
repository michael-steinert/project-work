package de.share_your_idea.user_management.service;

import de.share_your_idea.user_management.entity.UserEntity;
import de.share_your_idea.user_management.repository.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    @Mock
    private UserEntityRepository userEntityRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userEntityRepository, passwordEncoder);
    }

    @Test
    void shouldSaveUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Michael");
        userEntity.setPassword("Password");
        userEntity.setUser_role("ROLE_USER");

        given(userEntityRepository.save(any(UserEntity.class))).willReturn(any(UserEntity.class));

        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);

        UserEntity insertResult = userService.saveUser(userEntity);

        verify(userEntityRepository).save(captor.capture());

        UserEntity newUserEntity = captor.getValue();

        assertThat(newUserEntity.getUsername()).isEqualTo("Michael");
        assertThat(newUserEntity.getPassword()).isEqualTo(passwordEncoder.encode(userEntity.getPassword()));
        assertThat(newUserEntity.getUser_role()).isEqualTo("ROLE_USER");
        assertThat(insertResult).isEqualTo(any(UserEntity.class));
    }

    @Test
    void shouldFindUserEntityByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("Michael");
        userEntity.setPassword("Password");
        userEntity.setUser_role("ROLE_USER");
        given(userEntityRepository.findUserEntityByUsername(userEntity.getUsername())).willReturn(userEntity);

        UserEntity newUserEntity = userService.findByUsername(userEntity.getUsername());
        assertThat(newUserEntity.getUsername()).isEqualTo("Michael");
        assertThat(newUserEntity.getUser_role()).isEqualTo("ROLE_USER");
    }

    @Test
    void shouldFindAllUserEntities() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("Michael");
        userEntity1.setPassword("Password");
        userEntity1.setUser_role("ROLE_USER");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("Marie");
        userEntity2.setPassword("Password");
        userEntity2.setUser_role("ROLE_ADMIN");
        List<UserEntity> userEntityList = List.of(userEntity1, userEntity2);

        given(userEntityRepository.findAll()).willReturn(userEntityList);

        List<UserEntity> newUserEntityList = userService.findAllUsers();
        assertThat(newUserEntityList).hasSize(2);
        UserEntity newUserEntity = newUserEntityList.get(0);
        assertThat(newUserEntity.getUsername()).isEqualTo("Michael");
        assertThat(newUserEntity.getUser_role()).isEqualTo("ROLE_USER");
    }
}