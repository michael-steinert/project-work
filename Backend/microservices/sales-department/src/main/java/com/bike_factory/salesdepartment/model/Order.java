package com.bike_factory.salesdepartment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private final UUID orderUid;
    @NotNull
    private final UUID customerUid;
    @NotNull
    private final UUID bikeUid;
    @NotNull
    @Min(value = 1)
    private final float totalPrice;
    @NotNull
    private final LocalDate orderDate;

    public Order(@JsonProperty("orderUid") UUID orderUid,
                 @JsonProperty("customerUid") UUID customerUid,
                 @JsonProperty("bikeUid") UUID bikeUid,
                 @JsonProperty("totalPrice") float totalPrice,
                 @JsonProperty("orderDate") LocalDate orderDate) {
        this.orderUid = orderUid;
        this.customerUid = customerUid;
        this.bikeUid = bikeUid;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public UUID getOrderUid() {
        return orderUid;
    }

    public UUID getCustomerUid() {
        return customerUid;
    }

    public UUID getBikeUid() {
        return bikeUid;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public static Order newOrder(UUID orderUid, Order order) {
        return new Order(orderUid, order.getCustomerUid(), order.getBikeUid(), order.getTotalPrice(), order.getOrderDate());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderUid=" + orderUid +
                ", customerUid=" + customerUid +
                ", bikeUid=" + bikeUid +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                '}';
    }
}
