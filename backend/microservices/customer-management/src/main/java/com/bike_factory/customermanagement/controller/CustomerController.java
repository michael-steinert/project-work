package com.bike_factory.customermanagement.controller;

import com.bike_factory.customermanagement.model.Customer;
import com.bike_factory.customermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        // throw new ApiRequestException("Custom Exception");
        return customerService.getAllCustomers().get();
    }

    /*
    @GetMapping(path= "{customerId}/orders")
    public List<Order> getAllOrdersForCustomer(@PathVariable("customerId") UUID customerId) {
        return customerService.getAllOrdersForCustomer(customerId);
    }
    */

    @PostMapping
    public void addNewCustomer(@RequestBody @Valid Customer customer) {
        customerService.insertCustomer(customer);
    }
}
