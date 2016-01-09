package com.monolitospizza.services;

import com.monolitospizza.model.Crust;
import com.monolitospizza.model.Sauce;
import com.monolitospizza.model.Size;


import java.util.ArrayList;

/**
 * @author Matt Stine
 */
public class BasePizzaMenuOptions {
    private Iterable<Size> sizes = new ArrayList<>();
    private Iterable<Crust> crusts = new ArrayList<>();
    private Iterable<Sauce> sauces = new ArrayList<>();

    public BasePizzaMenuOptions(Iterable<Size> sizes, Iterable<Crust> crusts, Iterable<Sauce> sauces) {
        this.sizes = sizes;
        this.crusts = crusts;
        this.sauces = sauces;
    }

    public Iterable<Size> getSizes() {
        return sizes;
    }

    public Iterable<Crust> getCrusts() {
        return crusts;
    }

    public Iterable<Sauce> getSauces() {
        return sauces;
    }
}
