package com.monolitospizza.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matt Stine
 */
public class Half {
    private Set<Topping> toppings = new HashSet<>();

    public Set<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }
}
