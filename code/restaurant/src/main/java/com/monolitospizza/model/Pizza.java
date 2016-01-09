package com.monolitospizza.model;

import javax.persistence.*;
import java.math.BigDecimal;

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
    @Embedded
    private Half leftHalf;
    @Embedded
    private Half rightHalf;
    @OneToOne
    private Sauce sauce;

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
