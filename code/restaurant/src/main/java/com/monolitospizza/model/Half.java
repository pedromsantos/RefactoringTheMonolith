package com.monolitospizza.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Matt Stine
 */
@Entity
public class Half {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Topping> toppings = new HashSet<>();

    public Long getId() {
        return id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Half half = (Half) o;
        return Objects.equals(toppings, half.toppings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toppings);
    }

    public boolean containsTopping(Topping topping) {
        return this.toppings.contains(topping);
    }
}
