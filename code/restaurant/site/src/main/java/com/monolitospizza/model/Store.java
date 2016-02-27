package com.monolitospizza.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Stine
 */
@Entity
public class Store implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(address, store.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", address=" + address +
                '}';
    }
}
