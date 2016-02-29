package com.monolitospizza.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matt Stine
 */
public class Order {
    private OrderStatus orderStatus;
    private String type;
    private Customer customer;
    private List<Pizza> pizzas = new ArrayList<>();
    private BigDecimal price;
    private Long id;
    private Long storeId;

    public Order() {
    }

    public Order(String type, Customer customer) {
        this.type = type;
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderStatus=" + orderStatus +
                ", type='" + type + '\'' +
                ", customer=" + customer +
                ", pizzas=" + pizzas +
                ", price=" + price +
                ", id=" + id +
                ", storeId=" + storeId +
                '}';
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
