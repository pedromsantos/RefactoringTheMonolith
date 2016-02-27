package com.monolitospizza.integration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matt Stine
 */
public class OrderMessage {
    private String type;
    private CustomerMessage customerMessage;
    private List<PizzaMessage> pizzaMessages = new ArrayList<>();
    private BigDecimal price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CustomerMessage getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(CustomerMessage customerMessage) {
        this.customerMessage = customerMessage;
    }

    public List<PizzaMessage> getPizzaMessages() {
        return pizzaMessages;
    }

    public void addPizzaMessage(PizzaMessage pizzaMessage) {
        this.pizzaMessages.add(pizzaMessage);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
