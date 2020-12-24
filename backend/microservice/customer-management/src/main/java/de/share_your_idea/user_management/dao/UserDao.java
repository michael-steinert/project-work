package de.share_your_idea.user_management.dao;

import de.share_your_idea.user_management.model.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    List<UserEntity> selectAllCustomers();
    UserEntity selectCustomerByCustomerUid(UUID userUid);
    int insertCustomer(UserEntity userEntity);
    int updateCustomer(UUID customerUid, UserEntity userEntity);
    int deleteCustomerByCustomerUid(UUID customerUid);
}
