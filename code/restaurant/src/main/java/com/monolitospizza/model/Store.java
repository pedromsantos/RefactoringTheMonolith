package com.monolitospizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Matt Stine
 */
@Entity
public class Store {

    @Id
    @GeneratedValue
    private Long id;

    public Store(Long id) {
        this.id = id;
    }

    public Store() {
    }

    public Long getId() {
        return id;
    }

}
