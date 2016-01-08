package com.monolitospizza.model;

import javax.persistence.*;

/**
 * @author Matt Stine
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name="ADDRESS_ID")
    private Address address;

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }
}
