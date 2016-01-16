package com.monolitospizza.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Stine
 */
@Entity
public class Pizza {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Size size;
    @OneToOne
    private Crust crust;
    @OneToOne(cascade = CascadeType.ALL)
    private Half leftHalf;
    @OneToOne(cascade = CascadeType.ALL)
    private Half rightHalf;
    @OneToOne
    private Sauce sauce;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private Pizza() {
        leftHalf = new Half();
        rightHalf = new Half();
    }

    public Pizza(Size size, Crust crust, Sauce sauce) {
        this();
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
    }

    public Pizza (Long id, Size size, Crust crust, Sauce sauce) {
        this(size, crust, sauce);
        this.id = id;
    }


    public Size getSize() {
        return size;
    }

    public Crust getCrust() {
        return crust;
    }

    public Half getLeftHalf() {
        return leftHalf;
    }

    public Half getRightHalf() {
        return rightHalf;
    }

    public void addLeftTopping(Topping topping) {
        this.leftHalf.addTopping(topping);
    }

    public void removeLeftTopping(Topping topping) {
        this.leftHalf.removeTopping(topping);
    }

    public void addRightTopping(Topping topping) {
        this.rightHalf.addTopping(topping);
    }

    public void removeRightTopping(Topping topping) {
        this.rightHalf.removeTopping(topping);
    }

    public Sauce getSauce() {
        return sauce;
    }

    public BigDecimal getPrice() {
        return this.size.getPrice()
                .add(this.leftHalf.getPrice())
                .add(this.rightHalf.getPrice());
    }

    public void addTopping(Topping topping) {
        addLeftTopping(topping);
        addRightTopping(topping);
    }

    public void removeTopping(Topping topping) {
        removeLeftTopping(topping);
        removeRightTopping(topping);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(size, pizza.size) &&
                Objects.equals(crust, pizza.crust) &&
                Objects.equals(leftHalf, pizza.leftHalf) &&
                Objects.equals(rightHalf, pizza.rightHalf) &&
                Objects.equals(sauce, pizza.sauce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, crust, leftHalf, rightHalf, sauce);
    }

    public void setOrder(Order order) {
        notNull(order);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public boolean containsTopping(Topping topping) {
        return leftHalf.containsTopping(topping) || rightHalf.containsTopping(topping);
    }

    public boolean leftHalfContainsTopping(Topping topping) {
        return leftHalf.containsTopping(topping);
    }

    public boolean rightHalfContainsTopping(Topping topping) {
        return rightHalf.containsTopping(topping);
    }

    public boolean wholePizzaContainsTopping(Topping topping) {
        return leftHalfContainsTopping(topping) && rightHalfContainsTopping(topping);
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }
}
