package com.monolitospizza.model;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Matt Stine
 */
@Embeddable
public class Half {
    @OneToMany
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

    public BigDecimal getPrice() {
        return toppings
                .stream()
                .map(Topping::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
