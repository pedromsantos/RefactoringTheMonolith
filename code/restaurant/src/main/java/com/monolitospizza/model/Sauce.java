package com.monolitospizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
