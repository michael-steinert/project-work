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

    public List<UserEntity> selectAllCustomers() {
        String sql = "SELECT customer_id, first_name, last_name, gender, email, street, house_number, zip_code, city FROM customer";
        return jdbcTemplate.query(sql, mapCustomerFromDb());
    }

    public UserEntity selectCustomerByCustomerUid(UUID customerUid) {
        String sql = "SELECT customer_id, first_name, last_name, gender, email, street, house_number, zip_code, city FROM customer WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(sql, mapCustomerFromDb(), customerUid);
    }

    public int insertCustomer(UserEntity userEntity) {
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, gender, email, street, house_number, zip_code, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, userEntity.getCustomerUid(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getGender().name().toUpperCase(), userEntity.getEmail(), userEntity.getStreet(), userEntity.getHouseNumber(), userEntity.getZipCode(), userEntity.getCity());
    }

    public int updateCustomer(UUID customerUid, UserEntity userEntity) {
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, gender = ?, email = ?, street = ?, house_number = ?, zip_code = ?, city = ? WHERE customer_id = ?";
        return jdbcTemplate.update(sql, userEntity.getFirstName(), userEntity.getLastName(), userEntity.getGender().name().toUpperCase(), userEntity.getEmail(), userEntity.getStreet(), userEntity.getHouseNumber(), userEntity.getZipCode(), userEntity.getCity(), customerUid);
    }

    public int deleteCustomerByCustomerUid(UUID customerUid) {
        String sql = "DELETE FROM customer where customer_id = ?";
        return jdbcTemplate.update(sql, customerUid);
    }

    private RowMapper<UserEntity> mapCustomerFromDb() {
        return (resultSet, i) -> {
            String customerIdStr = resultSet.getString("customer_id");
            UUID customerId = UUID.fromString(customerIdStr);
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String genderStr = resultSet.getString("gender").toUpperCase();
            UserEntity.Gender gender = UserEntity.Gender.valueOf(genderStr);
            String email = resultSet.getString("email");
            String street = resultSet.getString("street");
            String houseNumber = resultSet.getString("house_number");
            Integer zipCode = Integer.parseInt(resultSet.getString("zip_code"));
            String city = resultSet.getString("city");
            return new UserEntity(customerId, firstName, lastName, gender, email, street, houseNumber, zipCode, city);
        };
    }
}