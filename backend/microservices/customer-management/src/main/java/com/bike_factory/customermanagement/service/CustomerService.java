package com.bike_factory.customermanagement.service;

import com.bike_factory.customermanagement.dao.CustomerDao;
import com.bike_factory.customermanagement.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Optional<List<Customer>> getAllCustomers() {
        return Optional.ofNullable(customerDao.selectAllCustomers());
    }

    public Optional<Customer> getCustomer(UUID customerUid) {
        return Optional.ofNullable(customerDao.selectCustomerByCustomerUid(customerUid));
    }

    public int updateCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = getCustomer(customer.getCustomerUid());
        if (optionalCustomer.isPresent()) {
            return customerDao.updateCustomer(customer);
        }
        throw new NotFoundException("Customer " + customer.getCustomerUid() + " not found.");
    }

    public int removeCustomer(UUID uid) {
        UUID userUid = getCustomer(uid)
                .map(Customer::getCustomerUid)
                .orElseThrow(() -> new NotFoundException("Customer " + uid + " not found."));
        return customerDao.deleteCustomerByCustomerUid(userUid);
    }

    public int insertCustomer(Customer customer) {
        return customerDao.insertCustomer(Customer.newCustomer(UUID.randomUUID(), customer));
    }
}