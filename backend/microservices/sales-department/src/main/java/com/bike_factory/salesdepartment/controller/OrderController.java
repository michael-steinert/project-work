package com.bike_factory.salesdepartment.controller;

import com.bike_factory.salesdepartment.model.Order;
import com.bike_factory.salesdepartment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders(/*@RequestHeader("Authorization") String jsonWebToken*/) {
        // throw new ApiRequestException("Order Exception");
        //orderService.fetchCustomer(jsonWebToken);
        return orderService.getAllOrders();
    }

    @PostMapping
    public void addNewOrder(@RequestBody @Valid Order order) {
        orderService.insertOrder(order);
    }
}
