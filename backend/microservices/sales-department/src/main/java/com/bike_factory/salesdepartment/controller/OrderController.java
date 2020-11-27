package com.bike_factory.salesdepartment.controller;

import com.bike_factory.salesdepartment.model.Order;
import com.bike_factory.salesdepartment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "{orderUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order fetchOrder(@PathVariable("orderUid") UUID orderUid) {
        return orderService.getOrder(orderUid).orElseThrow(() -> new NotFoundException("Order " + orderUid + " not found."));
    }

    @GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> fetchCustomers(/*@RequestHeader("Authorization") String jsonWebToken*/) {
        // throw new ApiRequestException("Order Exception");
        //orderService.fetchCustomer(jsonWebToken);
        orderService.fetchCustomer();
        return orderService.getAllOrders().get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertOrder(@RequestBody @Valid Order order) {
        orderService.insertOrder(order);
    }

    @PutMapping(path = "{orderUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateOrder(@PathVariable("orderUid") UUID orderUid, @RequestBody @Valid Order order) {
        return orderService.updateOrder(orderUid, order);
    }

    @DeleteMapping(path = "{orderUid}")
    public Integer deleteOrder(@PathVariable("orderUid") UUID orderUid) {
        return orderService.removeOrder(orderUid);
    }
}
