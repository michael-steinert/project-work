package de.share_your_idea.user_management.service;

import de.share_your_idea.user_management.dao.UserDao;
import de.share_your_idea.user_management.model.UserEntity;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        userService = new UserService(userDao);
    }

    @Test
    void shouldGetAllCustomers() {
        UUID customerUid = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(customerUid, "Michael", "Steinert", UserEntity.Gender.MALE, "steinert-michael@example.org", "Fuchskaute", "4", 58730, "Fröndenberg/ Ruhr");
        ImmutableList<UserEntity> orderImmutableList = new ImmutableList.Builder<UserEntity>().add(userEntity).build();

        given(userDao.selectAllCustomers()).willReturn(orderImmutableList);

        Optional<List<UserEntity>> allOptionalCustomers = userService.getAllCustomers();
        List<UserEntity> allUserEntities = allOptionalCustomers.get();
        UserEntity userEntityFromService = allUserEntities.get(0);

        assertThat(allOptionalCustomers.isPresent()).isTrue();
        assertThat(allUserEntities).hasSize(1);
        assertThat(userEntityFromService.getCustomerUid()).isNotNull();
        assertThat(userEntityFromService.getCustomerUid()).isEqualTo(customerUid);
        assertThat(userEntityFromService.getCustomerUid()).isInstanceOf(UUID.class);
        assertThat(userEntityFromService.getFirstName()).isEqualTo("Michael");
        assertThat(userEntityFromService.getLastName()).isEqualTo("Steinert");
        assertThat(userEntityFromService.getGender()).isEqualTo(UserEntity.Gender.MALE);
        assertThat(userEntityFromService.getEmail()).isEqualTo("steinert-michael@example.org");
        assertThat(userEntityFromService.getStreet()).isEqualTo("Fuchskaute");
        assertThat(userEntityFromService.getHouseNumber()).isEqualTo("4");
        assertThat(userEntityFromService.getZipCode()).isEqualTo(58730);
        assertThat(userEntityFromService.getCity()).isEqualTo("Fröndenberg/ Ruhr");
    }

    @Test
    void shouldGetCustomer() {
        UUID customerUid = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(customerUid, "Michael", "Steinert", UserEntity.Gender.MALE, "steinert-michael@example.org", "Fuchskaute", "4", 58730, "Fröndenberg/ Ruhr");

        given(userDao.selectCustomerByCustomerUid(customerUid)).willReturn(userEntity);

        Optional<UserEntity> optionalCustomerFromService = userService.getCustomer(customerUid);
        UserEntity userEntityFromService = optionalCustomerFromService.get();

        assertThat(optionalCustomerFromService.isPresent()).isTrue();
        assertThat(userEntityFromService.getCustomerUid()).isNotNull();
        assertThat(userEntityFromService.getCustomerUid()).isEqualTo(customerUid);
        assertThat(userEntityFromService.getCustomerUid()).isInstanceOf(UUID.class);
        assertThat(userEntityFromService.getFirstName()).isEqualTo("Michael");
        assertThat(userEntityFromService.getLastName()).isEqualTo("Steinert");
        assertThat(userEntityFromService.getGender()).isEqualTo(UserEntity.Gender.MALE);
        assertThat(userEntityFromService.getEmail()).isEqualTo("steinert-michael@example.org");
        assertThat(userEntityFromService.getStreet()).isEqualTo("Fuchskaute");
        assertThat(userEntityFromService.getHouseNumber()).isEqualTo("4");
        assertThat(userEntityFromService.getZipCode()).isEqualTo(58730);
        assertThat(userEntityFromService.getCity()).isEqualTo("Fröndenberg/ Ruhr");
    }

    @Test
    void shouldInsertCustomer() {
        UUID customerUid = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(customerUid, "Michael", "Steinert", UserEntity.Gender.MALE, "steinert-michael@example.org", "Fuchskaute", "4", 58730, "Fröndenberg/ Ruhr");
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);

        given(userDao.insertCustomer(any(UserEntity.class))).willReturn(1);

        int insertResult = userService.insertCustomer(userEntity);
        verify(userDao).insertCustomer(captor.capture());
        UserEntity orderFromCaptor = captor.getValue();

        assertThat(orderFromCaptor.getCustomerUid()).isNotNull();
        assertThat(orderFromCaptor.getCustomerUid()).isInstanceOf(UUID.class);
        assertThat(orderFromCaptor.getFirstName()).isEqualTo("Michael");
        assertThat(orderFromCaptor.getLastName()).isEqualTo("Steinert");
        assertThat(orderFromCaptor.getGender()).isEqualTo(UserEntity.Gender.MALE);
        assertThat(orderFromCaptor.getEmail()).isEqualTo("steinert-michael@example.org");
        assertThat(orderFromCaptor.getStreet()).isEqualTo("Fuchskaute");
        assertThat(orderFromCaptor.getHouseNumber()).isEqualTo("4");
        assertThat(orderFromCaptor.getZipCode()).isEqualTo(58730);
        assertThat(orderFromCaptor.getCity()).isEqualTo("Fröndenberg/ Ruhr");
    }
}
