package com.bike_factory.salesdepartment.service;

import com.bike_factory.salesdepartment.dao.OrderDao;
import com.bike_factory.salesdepartment.model.Customer;
import com.bike_factory.salesdepartment.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private OrderDao orderDao;

    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Optional<List<Order>> getAllOrders() {
        return Optional.ofNullable(orderDao.selectAllOrders());
    }

    public Optional<Order> getOrder(UUID orderUid) {
        return Optional.ofNullable(orderDao.selectOrderByOrderUid(orderUid));
    }

    public int updateOrder(Order order) {
        Optional<Order> optionalOrder = getOrder(order.getOrderUid());
        if (optionalOrder.isPresent()) {
            return orderDao.updateOrder(order);
        }
        throw new NotFoundException("Order " + order.getOrderUid() + " not found.");
    }

    public int removeOrder(UUID uid) {
        UUID orderUid = getOrder(uid)
                .map(Order::getOrderUid)
                .orElseThrow(() -> new NotFoundException("Order " + uid + " not found."));
        return orderDao.deleteOrderByOrderUid(orderUid);
    }

    public int insertOrder(Order order) {
        return orderDao.insertOrder(Order.newOrder(UUID.randomUUID(), order));
    }

    public void fetchCustomer(/*String jsonWebToken*/) {
        for(Customer customer: orderDao.fetchCustomer(/*jsonWebToken*/)) {
            System.out.println(customer);
        }
    }
}
