package com.monolitospizza.model;

import javax.persistence.*;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Stine
 */
@Entity
public class Store {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    public Store(Long id, Address address) {
        this(address);
        this.id = id;
    }

    public Store() {
    }

    public Store(Address address) {
        notNull(address);
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }
}
