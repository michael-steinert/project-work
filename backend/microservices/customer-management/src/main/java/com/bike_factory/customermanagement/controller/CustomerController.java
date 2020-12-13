package com.bike_factory.customermanagement.controller;

import com.bike_factory.customermanagement.model.Customer;
import com.bike_factory.customermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "{customerUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer fetchCustomer(@PathVariable("customerUid") UUID customerUid) {
        return customerService.getCustomer(customerUid).orElseThrow(() -> new NotFoundException("Customer " + customerUid + " not found."));
    }

    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> fetchCustomers() {
        // throw new ApiRequestException("Custom Exception");
        return customerService.getAllCustomers().get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer insertCustomer(@RequestBody @Valid Customer customer) {
        return customerService.insertCustomer(customer);
    }

    @PutMapping(path = "{customerUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateCustomer(@PathVariable("customerUid") UUID customerUid, @RequestBody @Valid Customer customer) {
        return customerService.updateCustomer(customerUid, customer);
    }

    @DeleteMapping(path = "{customerUid}")
    public Integer deleteCustomer(@PathVariable("customerUid") UUID customerUid) {
        return customerService.removeCustomer(customerUid);
    }

    /*
    TODO
    @GetMapping(path= "{customerId}/orders")
    public List<Order> fetchOrdersForCustomer(@PathVariable("customerUid") UUID customerUid) {
        return customerService.getAllOrdersForCustomer(customerUid);
    }
    */
}
