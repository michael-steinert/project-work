package com.bike_factory.usermanagement.dao;

import com.bike_factory.usermanagement.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CustomerDataAccessService implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> selectAllCustomers() {
        String sql = "SELECT customer_id, first_name, last_name, gender, email, street, house_number, zip_code, city FROM customer";
        return jdbcTemplate.query(sql, mapCustomerFromDb());
    }

    public Customer selectCustomerByCustomerUid(UUID customerUid) {
        String sql = "SELECT customer_id, first_name, last_name, gender, email, street, house_number, zip_code, city FROM customer WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(sql, mapCustomerFromDb(), customerUid);
    }

    public int insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, gender, email, street, house_number, zip_code, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, customer.getCustomerUid(), customer.getFirstName(), customer.getLastName(), customer.getGender().name().toUpperCase(), customer.getEmail(), customer.getStreet(), customer.getHouseNumber(), customer.getZipCode(), customer.getCity());
    }

    public int updateCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, gender, email, street, house_number, zip_code, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, customer.getCustomerUid(), customer.getFirstName(), customer.getLastName(), customer.getGender().name().toUpperCase(), customer.getEmail(), customer.getStreet(), customer.getHouseNumber(), customer.getZipCode(), customer.getCity());
    }

    public int deleteCustomerByCustomerUid(UUID customerUid) {
        String sql = "DELETE FROM customer where customer_id = ?";
        return jdbcTemplate.update(sql, customerUid);
    }

    private RowMapper<Customer> mapCustomerFromDb() {
        return (resultSet, i) -> {
            String customerIdStr = resultSet.getString("customer_id");
            UUID customerId = UUID.fromString(customerIdStr);
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String genderStr = resultSet.getString("gender").toUpperCase();
            Customer.Gender gender = Customer.Gender.valueOf(genderStr);
            String email = resultSet.getString("email");
            String street = resultSet.getString("street");
            String houseNumber = resultSet.getString("house_number");
            Integer zipCode = Integer.parseInt(resultSet.getString("zip_code"));
            String city = resultSet.getString("city");
            return new Customer(customerId, firstName, lastName, gender, email, street, houseNumber, zipCode, city);
        };
    }
}