package com.monolitospizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Matt Stine
 */
@Entity
public class Size {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;


    public Size(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return Objects.equals(name, size.name) &&
                Objects.equals(price, size.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
