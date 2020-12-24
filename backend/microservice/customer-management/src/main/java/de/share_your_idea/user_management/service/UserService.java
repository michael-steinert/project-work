package de.share_your_idea.user_management.service;

import de.share_your_idea.user_management.dao.UserDao;
import de.share_your_idea.user_management.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    private final UserDao userDao;
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(UserDao userDao, RestTemplate restTemplate) {
        this.userDao = userDao;
        this.restTemplate = restTemplate;
    }

    public Optional<List<UserEntity>> getAllUsers() {
        log.info("User Service: Select all Users");
        return Optional.ofNullable(userDao.selectAllUsers());
    }

    public Optional<UserEntity> getUserByUserUid(UUID userUid) {
        log.info("User Service: Select User by UserUid");
        return Optional.ofNullable(userDao.selectUserByUserUid(userUid));
    }

    public int updateUserByUserUid(UUID userUid, UserEntity userEntity) {
        Optional<UserEntity> optionalCustomer = getUserByUserUid(userUid);
        if (optionalCustomer.isPresent()) {
            log.info("User Service: Update User by UserUid");
            return userDao.updateUser(userUid, userEntity);
        }
        throw new NotFoundException("User with UserUid " + userUid + " not found.");
    }

    public int removeUser(UUID userUid) {
        UUID tmpUserUid = getUserByUserUid(userUid)
                .map(UserEntity::getUserUid)
                .orElseThrow(() -> new NotFoundException("User with UserUid " + userUid + " not found."));
        log.info("User Service: Delete User by UserUid");
        return userDao.deleteUserByUserUid(tmpUserUid);
    }

    public int insertUser(UserEntity userEntity) {
        log.info("User Service: Insert Customer");
        return userDao.insertUser(UserEntity.newUser(UUID.randomUUID(), userEntity));
    }

    /*
    public Optional<List<Object>> getAllOrders() {
        log.info("CustomerService: Select all Orders");
        Object order = restTemplate.getForObject("http://ORDER-DEPARTMENT_SERVICE/orders/" + orderUid, Object.class);
        return Optional.ofNullable(customerDao.selectAllCustomers());
    }
     */
}