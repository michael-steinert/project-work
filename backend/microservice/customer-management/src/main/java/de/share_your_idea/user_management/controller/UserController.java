package de.share_your_idea.user_management.controller;

import de.share_your_idea.user_management.model.UserEntity;
import de.share_your_idea.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("customer")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{customerUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity fetchCustomer(@PathVariable("customerUid") UUID customerUid) {
        return userService.getCustomer(customerUid).orElseThrow(() -> new NotFoundException("Customer " + customerUid + " not found."));
    }

    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEntity> fetchCustomers() {
        // throw new ApiRequestException("Custom Exception");
        return userService.getAllCustomers().get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer insertCustomer(@RequestBody @Valid UserEntity userEntity) {
        return userService.insertCustomer(userEntity);
    }

    @PutMapping(path = "{customerUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateCustomer(@PathVariable("customerUid") UUID customerUid, @RequestBody @Valid UserEntity userEntity) {
        return userService.updateCustomer(customerUid, userEntity);
    }

    @DeleteMapping(path = "{customerUid}")
    public Integer deleteCustomer(@PathVariable("customerUid") UUID customerUid) {
        return userService.removeCustomer(customerUid);
    }

    /*
    TODO
    @GetMapping(path= "{customerId}/orders")
    public List<Order> fetchOrdersForCustomer(@PathVariable("customerUid") UUID customerUid) {
        return customerService.getAllOrdersForCustomer(customerUid);
    }
    */
}
