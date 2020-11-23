package com.bike_factory.salesdepartment.dao;

import com.bike_factory.salesdepartment.model.Customer;
import com.bike_factory.salesdepartment.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderDao {
    List<Order> selectAllOrders();
    Order selectOrderByOrderUid(UUID orderUid);
    int insertOrder(Order order);
    int updateOrder(Order order);
    int deleteOrderByOrderUid(UUID orderUid);
    List<Customer> fetchCustomer(String jsonWebToken);
}