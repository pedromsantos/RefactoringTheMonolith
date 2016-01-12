package com.monolitospizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author Matt Stine
 */
@Entity
public class Sauce {

    private String normal;
    @Id
    @GeneratedValue
    private Long id;

    public Sauce(String normal) {
           this.normal = normal;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sauce sauce = (Sauce) o;
        return Objects.equals(normal, sauce.normal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normal);
    }
}
