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

    @Autowired
    private RestTemplate restTemplate;

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<List<UserEntity>> getAllCustomers() {
        log.info("CustomerService: Select all Customers");
        return Optional.ofNullable(userDao.selectAllCustomers());
    }

    public Optional<UserEntity> getCustomer(UUID customerUid) {
        log.info("CustomerService: Select Customer by CustomerUid");
        return Optional.ofNullable(userDao.selectCustomerByCustomerUid(customerUid));
    }

    public int updateCustomer(UUID customerUid, UserEntity userEntity) {
        Optional<UserEntity> optionalCustomer = getCustomer(customerUid);
        if (optionalCustomer.isPresent()) {
            log.info("CustomerService: Update Customer by CustomerUid");
            return userDao.updateCustomer(customerUid, userEntity);
        }
        throw new NotFoundException("Customer " + customerUid + " not found.");
    }

    public int removeCustomer(UUID uid) {
        UUID userUid = getCustomer(uid)
                .map(UserEntity::getCustomerUid)
                .orElseThrow(() -> new NotFoundException("Customer " + uid + " not found."));
        log.info("CustomerService: Delete Customer by CustomerUid");
        return userDao.deleteCustomerByCustomerUid(userUid);
    }

    public int insertCustomer(UserEntity userEntity) {
        log.info("CustomerService: Insert Customer");
        return userDao.insertCustomer(UserEntity.newCustomer(UUID.randomUUID(), userEntity));
    }

    /*
    public Optional<List<Object>> getAllOrders() {
        log.info("CustomerService: Select all Orders");
        Object order = restTemplate.getForObject("http://ORDER-DEPARTMENT_SERVICE/orders/" + orderUid, Object.class);
        return Optional.ofNullable(customerDao.selectAllCustomers());
    }
     */
}