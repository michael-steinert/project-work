package de.share_your_idea.user_management.service;

import de.share_your_idea.user_management.dao.UserDao;
import de.share_your_idea.user_management.model.UserEntity;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserDao userDao;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userDao, new RestTemplate());
    }

    @Test
    void shouldGetAllCustomers() {
        UUID userUid = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(userUid, "Micha", "", "ROLE_USER", "Michael", "Steinert","steinert-michael@example.org");
        ImmutableList<UserEntity> userImmutableList = new ImmutableList.Builder<UserEntity>().add(userEntity).build();

        given(userDao.selectAllUsers()).willReturn(userImmutableList);

        Optional<List<UserEntity>> allOptionalUsers = userService.getAllUsers();
        List<UserEntity> allUserEntities = allOptionalUsers.get();
        UserEntity userEntityFromService = allUserEntities.get(0);

        assertThat(allOptionalUsers.isPresent()).isTrue();
        assertThat(allUserEntities).hasSize(1);
        assertThat(userEntityFromService.getUserUid()).isNotNull();
        assertThat(userEntityFromService.getUserUid()).isEqualTo(userUid);
        assertThat(userEntityFromService.getUserUid()).isInstanceOf(UUID.class);
        assertThat(userEntityFromService.getUsername()).isEqualTo("Micha");
        assertThat(userEntityFromService.getUser_role()).isEqualTo("ROLE_USER");
        assertThat(userEntityFromService.getFirstName()).isEqualTo("Michael");
        assertThat(userEntityFromService.getLastName()).isEqualTo("Steinert");
        assertThat(userEntityFromService.getEmail()).isEqualTo("steinert-michael@example.org");
    }

    @Test
    void shouldGetUser() {
        UUID userUid = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(userUid, "Micha", "", "ROLE_USER", "Michael", "Steinert","steinert-michael@example.org");

        given(userDao.selectUserByUserUid(userUid)).willReturn(userEntity);

        Optional<UserEntity> optionalCustomerFromService = userService.getUserByUserUid(userUid);
        UserEntity userEntityFromService = optionalCustomerFromService.get();

        assertThat(optionalCustomerFromService.isPresent()).isTrue();
        assertThat(userEntityFromService.getUserUid()).isNotNull();
        assertThat(userEntityFromService.getUserUid()).isEqualTo(userUid);
        assertThat(userEntityFromService.getUserUid()).isInstanceOf(UUID.class);
        assertThat(userEntityFromService.getUsername()).isEqualTo("Micha");
        assertThat(userEntityFromService.getUser_role()).isEqualTo("ROLE_USER");
        assertThat(userEntityFromService.getFirstName()).isEqualTo("Michael");
        assertThat(userEntityFromService.getLastName()).isEqualTo("Steinert");
        assertThat(userEntityFromService.getEmail()).isEqualTo("steinert-michael@example.org");
    }

    @Test
    void shouldInsertCustomer() {
        UUID userUid = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(userUid, "Micha", "", "ROLE_USER", "Michael", "Steinert","steinert-michael@example.org");
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);

        given(userDao.insertUser(any(UserEntity.class))).willReturn(1);

        int insertResult = userService.insertUser(userEntity);
        verify(userDao).insertUser(captor.capture());
        UserEntity orderFromCaptor = captor.getValue();

        assertThat(orderFromCaptor.getUserUid()).isNotNull();
        assertThat(orderFromCaptor.getUserUid()).isInstanceOf(UUID.class);
        assertThat(orderFromCaptor.getUsername()).isEqualTo("Micha");
        assertThat(orderFromCaptor.getUser_role()).isEqualTo("ROLE_USER");
        assertThat(orderFromCaptor.getFirstName()).isEqualTo("Michael");
        assertThat(orderFromCaptor.getLastName()).isEqualTo("Steinert");
        assertThat(orderFromCaptor.getEmail()).isEqualTo("steinert-michael@example.org");
    }
}
