package com.bike_factory.customermanagement.service;

import com.bike_factory.customermanagement.dao.CustomerDao;
import com.bike_factory.customermanagement.model.Customer;
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
public class CustomerService {

    @Autowired
    private RestTemplate restTemplate;

    private CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Optional<List<Customer>> getAllCustomers() {
        log.info("CustomerService: Select all Customers");
        return Optional.ofNullable(customerDao.selectAllCustomers());
    }

    public Optional<Customer> getCustomer(UUID customerUid) {
        log.info("CustomerService: Select Customer by CustomerUid");
        return Optional.ofNullable(customerDao.selectCustomerByCustomerUid(customerUid));
    }

    public int updateCustomer(UUID customerUid, Customer customer) {
        Optional<Customer> optionalCustomer = getCustomer(customerUid);
        if (optionalCustomer.isPresent()) {
            log.info("CustomerService: Update Customer by CustomerUid");
            return customerDao.updateCustomer(customerUid, customer);
        }
        throw new NotFoundException("Customer " + customerUid + " not found.");
    }

    public int removeCustomer(UUID uid) {
        UUID userUid = getCustomer(uid)
                .map(Customer::getCustomerUid)
                .orElseThrow(() -> new NotFoundException("Customer " + uid + " not found."));
        log.info("CustomerService: Delete Customer by CustomerUid");
        return customerDao.deleteCustomerByCustomerUid(userUid);
    }

    public int insertCustomer(Customer customer) {
        log.info("CustomerService: Insert Customer");
        return customerDao.insertCustomer(Customer.newCustomer(UUID.randomUUID(), customer));
    }

    /*
    public Optional<List<Object>> getAllOrders() {
        log.info("CustomerService: Select all Orders");
        Object order = restTemplate.getForObject("http://ORDER-DEPARTMENT_SERVICE/orders/" + orderUid, Object.class);
        return Optional.ofNullable(customerDao.selectAllCustomers());
    }
     */
}