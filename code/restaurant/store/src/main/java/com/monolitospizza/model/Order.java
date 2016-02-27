package com.monolitospizza.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matt Stine
 */
@Entity
public class Order {
    private String type;
    @OneToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Pizza> pizzas = new ArrayList<>();
    private BigDecimal price;
    @Id
    @GeneratedValue
    private Long id;
    private Long storeId;

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

    public void addPizzaMessage(Pizza pizza) {
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
        return "OrderMessage{" +
                "type='" + type + '\'' +
                ", customerMessage=" + customer +
                ", pizzaMessages=" + pizzas +
                ", price=" + price +
                ", id=" + id +
                ", storeId=" + storeId +
                '}';
    }
}
