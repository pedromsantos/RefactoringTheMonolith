package com.monolitospizza.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matt Stine
 */
public class Order {
    private List<Pizza> pizzas = new ArrayList<>();

    public BigDecimal getPrice() {
        return pizzas
                .stream()
                .map(Pizza::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }
}
