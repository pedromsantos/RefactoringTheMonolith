package com.monolitospizza.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Stine
 */
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Pizza> pizzas = new ArrayList<>();
    private OrderType type;

    @OneToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="STORE_ID")
    private Store store;

    private Order() {
    }

    public Order(Long id, OrderType type, Customer customer, Store store) {
        this(type, customer, store);
        this.id = id;
    }

    public Order(OrderType type, Customer customer, Store store) {
        notNull(customer);
        notNull(store);

        if (type == OrderType.FOR_DELIVERY) {
            notNull(customer.getAddress());
        }

        this.type = type;
        this.customer = customer;
        this.store = store;
    }

    public BigDecimal getPrice() {
        return pizzas
                .stream()
                .map(Pizza::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
        pizza.setOrder(this);
    }

    public OrderType getType() {
        return type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Long getId() {
        return id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(pizzas, order.pizzas) &&
                type == order.type &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzas, type, customer);
    }

    public Pizza getNewestPizza() {
        return pizzas.get(pizzas.size() - 1);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizzas=" + pizzas +
                ", type=" + type +
                ", customer=" + customer +
                '}';
    }

    public Store getStore() {
        return store;
    }
}

