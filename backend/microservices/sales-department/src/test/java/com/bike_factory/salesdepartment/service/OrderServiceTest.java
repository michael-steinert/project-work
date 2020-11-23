package com.bike_factory.salesdepartment.service;

import com.bike_factory.salesdepartment.dao.OrderDao;
import com.bike_factory.salesdepartment.model.Order;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class OrderServiceTest {

    @Mock
    private OrderDao orderDao;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(orderDao);
    }

    @Test
    void shouldGetAllOrders() {
        UUID orderUid = UUID.randomUUID();
        Order order = new Order(orderUid, UUID.randomUUID(), UUID.randomUUID(), 199.94f, LocalDate.now());
        ImmutableList<Order> orderImmutableList = new ImmutableList.Builder<Order>().add(order).build();

        given(orderDao.selectAllOrders()).willReturn(orderImmutableList);

        Optional<List<Order>> allOptionalOrders = orderService.getAllOrders();
        List<Order> allOrders = allOptionalOrders.get();
        Order orderFromService = allOrders.get(0);

        assertThat(allOptionalOrders.isPresent()).isTrue();
        assertThat(allOrders).hasSize(1);
        assertThat(orderFromService.getOrderUid()).isNotNull();
        assertThat(orderFromService.getOrderUid()).isEqualTo(orderUid);
        assertThat(orderFromService.getCustomerUid()).isInstanceOf(UUID.class);
        assertThat(orderFromService.getBikeUid()).isInstanceOf(UUID.class);
        assertThat(orderFromService.getTotalPrice()).isEqualTo(199.94f);
        assertThat(orderFromService.getOrderDate()).isInstanceOf(LocalDate.class);
    }

    @Test
    void shouldGetOrder() {
        UUID orderUid = UUID.randomUUID();
        Order order = new Order(orderUid, UUID.randomUUID(), UUID.randomUUID(), 199.94f, LocalDate.now());

        given(orderDao.selectOrderByOrderUid(orderUid)).willReturn(order);

        Optional<Order> optionalOrderFromService = orderService.getOrder(orderUid);
        Order orderFromService = optionalOrderFromService.get();

        assertThat(optionalOrderFromService.isPresent()).isTrue();
        assertThat(orderFromService.getOrderUid()).isNotNull();
        assertThat(orderFromService.getOrderUid()).isEqualTo(orderUid);
        assertThat(orderFromService.getCustomerUid()).isInstanceOf(UUID.class);
        assertThat(orderFromService.getBikeUid()).isInstanceOf(UUID.class);
        assertThat(orderFromService.getTotalPrice()).isEqualTo(199.94f);
        assertThat(orderFromService.getOrderDate()).isInstanceOf(LocalDate.class);
    }

    @Test
    void shouldInsertOrder() {
        UUID orderUid = UUID.randomUUID();
        Order order = new Order(orderUid, UUID.randomUUID(), UUID.randomUUID(), 199.94f, LocalDate.now());
        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

        given(orderDao.insertOrder(any(Order.class))).willReturn(1);

        int insertResult = orderService.insertOrder(order);
        verify(orderDao).insertOrder(captor.capture());
        Order orderFromCaptor = captor.getValue();

        assertThat(insertResult).isEqualTo(1);
        assertThat(orderFromCaptor.getOrderUid()).isNotNull();
        assertThat(orderFromCaptor.getOrderUid()).isInstanceOf(UUID.class);
        assertThat(orderFromCaptor.getCustomerUid()).isInstanceOf(UUID.class);
        assertThat(orderFromCaptor.getBikeUid()).isInstanceOf(UUID.class);
        assertThat(orderFromCaptor.getTotalPrice()).isEqualTo(199.94f);
        assertThat(orderFromCaptor.getOrderDate()).isInstanceOf(LocalDate.class);
    }
}
