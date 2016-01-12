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

    private String name;
    @Id
    @GeneratedValue
    private Long id;

    private Sauce() {
    }

    public Sauce(String name) {
           this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sauce sauce = (Sauce) o;
        return Objects.equals(name, sauce.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
