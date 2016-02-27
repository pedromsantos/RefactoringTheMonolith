package com.monolitospizza.model;

import javax.persistence.*;

/**
 * @author Matt Stine
 */
@Entity
public class Topping {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;
    @ManyToOne
    @JoinColumn(name = "PIZZA_ID")
    private Pizza pizza;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ToppingMessage{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
