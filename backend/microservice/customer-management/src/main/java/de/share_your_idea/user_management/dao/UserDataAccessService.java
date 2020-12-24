package de.share_your_idea.user_management.dao;

import de.share_your_idea.user_management.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserEntity> selectAllUsers() {
        String sql = "SELECT user_uid, username, first_name, last_name, email FROM user_table";
        return jdbcTemplate.query(sql, mapUserFromDb());
    }

    public UserEntity selectUserByUserUid(UUID userUid) {
        String sql = "SELECT user_uid, username, first_name, last_name, email FROM customer WHERE user_uid = ?";
        return jdbcTemplate.queryForObject(sql, mapUserFromDb(), userUid);
    }

    public int insertUser(UserEntity userEntity) {
        String sql = "INSERT INTO user_table (user_uid, username, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, userEntity.getUserUid(), userEntity.getUsername(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail());
    }

    public int updateUser(UUID userUid, UserEntity userEntity) {
        String sql = "UPDATE user_table SET username = ?, first_name = ?, last_name = ?, email = ? WHERE user_uid = ?";
        return jdbcTemplate.update(sql, userEntity.getUsername(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), userUid);
    }

    public int deleteUserByUserUid(UUID userUid) {
        String sql = "DELETE FROM user_table where user_uid = ?";
        return jdbcTemplate.update(sql, userUid);
    }

    private RowMapper<UserEntity> mapUserFromDb() {
        return (resultSet, i) -> {
            String userUidStr = resultSet.getString("user_uid");
            UUID userUid = UUID.fromString(userUidStr);
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            return new UserEntity(userUid, username, firstName, lastName, email);
        };
    }
}