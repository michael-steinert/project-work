package com.bike_factory.customermanagement.dao;

import com.bike_factory.customermanagement.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
    Customer selectCustomerByCustomerUid(UUID userUid);
    int insertCustomer(Customer customer);
    int updateCustomer(Customer customer);
    int deleteCustomerByCustomerUid(UUID customerUid);
}
