package com.monolitospizza.model;

/**
 * @author Matt Stine
 */
public class Pizza {

    private Size size;
    private Crust crust;
    private Half leftHalf;
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
}
