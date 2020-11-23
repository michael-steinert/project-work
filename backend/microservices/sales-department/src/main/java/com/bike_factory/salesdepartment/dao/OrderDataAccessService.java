package com.bike_factory.salesdepartment.dao;

import com.bike_factory.salesdepartment.model.Customer;
import com.bike_factory.salesdepartment.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderDataAccessService implements OrderDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Order> selectAllOrders() {
        String sql = "SELECT order_id, customer_id, bike_id, total_price, order_date FROM orders";
        return jdbcTemplate.query(sql, mapOrderFromDb());
    }

    public Order selectOrderByOrderUid(UUID orderUid) {
        String sql = "SELECT order_id, customer_id, bike_id, total_price, order_date FROM orders WHERE order_id = ?";
        return jdbcTemplate.queryForObject(sql, mapOrderFromDb(), orderUid);
    }

    public int insertOrder(Order order) {
        String sql = "INSERT INTO orders (order_id, customer_id, bike_id, total_price, order_date) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, order.getOrderUid(), order.getCustomerUid(), order.getBikeUid(), order.getTotalPrice(), order.getOrderDate());
    }

    public int updateOrder(Order order) {
        String sql = "INSERT INTO orders (order_id, customer_id, bike_id, total_price, order_date) VALUES ((?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, order.getOrderUid(), order.getCustomerUid(), order.getBikeUid(), order.getTotalPrice(), order.getOrderDate());
    }

    public int deleteOrderByOrderUid(UUID orderUid) {
        String sql = "DELETE FROM orders where order_id = ?";
        return jdbcTemplate.update(sql, orderUid);
    }

    public List<Customer> fetchCustomer(String jsonWebToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jsonWebToken);
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        //headers.setContentType(MediaType.APPLICATION_JSON); Actualy only Get-Method and not Post-Method
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Customer>> response = restTemplate
            .exchange("http://localhost:8081/api/v1/customers", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Customer>>() {});
        return response.getBody();
    }

    private RowMapper<Order> mapOrderFromDb() {
        return (resultSet, i) -> {
            String orderIdStr = resultSet.getString("order_id");
            UUID orderUid = UUID.fromString(orderIdStr);
            String customerIdStr = resultSet.getString("customer_id");
            UUID customerUid = UUID.fromString(customerIdStr);
            String bikeIdStr = resultSet.getString("bike_id");
            UUID bikeUid = UUID.fromString(bikeIdStr);
            float totalPrice = Float.parseFloat(resultSet.getString("total_price"));
            LocalDate orderDate = resultSet.getDate("order_date").toLocalDate();
            return new Order(orderUid, customerUid, bikeUid, totalPrice, orderDate);
        };
    }
}
