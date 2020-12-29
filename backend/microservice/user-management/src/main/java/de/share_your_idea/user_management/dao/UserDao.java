package de.share_your_idea.user_management.dao;

import de.share_your_idea.user_management.model.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    List<UserEntity> selectAllUsers();
    UserEntity selectUserByUserUid(UUID userUid);
    int insertUser(UserEntity userEntity);
    int updateUser(UUID customerUid, UserEntity userEntity);
    int deleteUserByUserUid(UUID userUid);
}