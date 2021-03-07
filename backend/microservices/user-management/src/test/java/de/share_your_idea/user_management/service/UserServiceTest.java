package de.share_your_idea.user_management.service;

import de.share_your_idea.user_management.entity.UserEntity;
import de.share_your_idea.user_management.repository.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static de.share_your_idea.user_management.entity.UserRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class UserServiceTest {
    @Mock
    private UserEntityRepository userEntityRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Captor
    private ArgumentCaptor<UserEntity> userEntityArgumentCaptor;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userEntityRepository, passwordEncoder);
    }

    @Test
    void itShouldSaveUserEntity() {
        /* Given */
        UserEntity userEntity = new UserEntity(1L, "Michael", "testPassword", ROLE_USER, "testAuthorizationToken");
        /* Mocking the Return if Method save() is called */
        given(userEntityRepository.save(any(UserEntity.class))).willReturn(any(UserEntity.class));
        /* When */
        UserEntity insertResult = userService.saveUser(userEntity);
        /* Then */
        then(userEntityRepository).should().save(userEntityArgumentCaptor.capture());
        UserEntity userEntityFromService = userEntityArgumentCaptor.getValue();
        assertThat(userEntityFromService.getUserId()).isEqualTo(userEntity.getUserId());
        assertThat(userEntityFromService.getUsername()).isEqualTo(userEntity.getUsername());
        assertThat(userEntityFromService.getPassword()).isEqualTo(passwordEncoder.encode(userEntity.getPassword()));
        assertThat(userEntityFromService.getUserRole()).isEqualTo(userEntity.getUserRole());
        assertThat(insertResult).isEqualTo(any(UserEntity.class));
    }

    @Test
    void shouldFindUserEntityByUsername() {
        /* Given */
        UserEntity userEntity = new UserEntity(1L, "Michael", "testPassword", ROLE_USER, "testAuthorizationToken");
        /* Mocking the Return if Method save() is called */
        given(userEntityRepository.findUserEntityByUsername(userEntity.getUsername())).willReturn(Optional.of(userEntity));
        /* When */
        userService.saveUser(userEntity);
        /* Then */
        Optional<UserEntity> userEntityOptional = then(userEntityRepository).should().findUserEntityByUsername(userEntity.getUsername());
        UserEntity userEntityFromService = userEntityOptional.get();
        assertThat(userEntityFromService.getUserId()).isEqualTo(userEntity.getUserId());
        assertThat(userEntityFromService.getUsername()).isEqualTo(userEntity.getUsername());
        assertThat(userEntityFromService.getPassword()).isEqualTo(passwordEncoder.encode(userEntity.getPassword()));
        assertThat(userEntityFromService.getUserRole()).isEqualTo(userEntity.getUserRole());
    }
}