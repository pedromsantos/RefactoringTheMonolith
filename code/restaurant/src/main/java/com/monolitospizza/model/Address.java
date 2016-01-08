package com.monolitospizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Matt Stine
 */
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;

    public Address(String streetAddress, String city, String state, String postalCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
