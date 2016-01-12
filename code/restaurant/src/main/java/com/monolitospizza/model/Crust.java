package com.monolitospizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author Matt Stine
 */
@Entity
public class Crust {

    private String name;
    @Id
    @GeneratedValue
    private Long id;

    public Crust(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crust crust = (Crust) o;
        return Objects.equals(name, crust.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
