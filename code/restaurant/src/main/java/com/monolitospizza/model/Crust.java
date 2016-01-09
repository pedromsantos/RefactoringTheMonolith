package com.monolitospizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
