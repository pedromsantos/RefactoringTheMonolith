package com.monolitospizza.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author Matt Stine
 */
@Entity
public class Pizza {

    @Id
    @GeneratedValue
    private Long id;
    private Size size;
    private Crust crust;
    @Embedded
    private Half leftHalf;
    @Embedded
    private Half rightHalf;
    private Sauce sauce;

    public Pizza() {
        leftHalf = new Half();
        rightHalf = new Half();

        size = Size.LARGE;
        crust = Crust.THIN;
        sauce = Sauce.NORMAL;
    }

    public Pizza(Size size) {
        this();
        this.size = size;
    }

    public Pizza(Crust crust) {
        this();
        this.crust = crust;
    }

    public Pizza(Sauce sauce) {
        this();
        this.sauce = sauce;
    }

    public Pizza(Size size, Crust crust) {
        this();
        this.size = size;
        this.crust = crust;
    }

    public Pizza(Size size, Sauce sauce) {
        this();
        this.size = size;
        this.sauce = sauce;
    }

    public Pizza(Crust crust, Sauce sauce) {
        this();
        this.crust = crust;
        this.sauce = sauce;
    }

    public Pizza(Size size, Crust crust, Sauce sauce) {
        this();
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
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
}
