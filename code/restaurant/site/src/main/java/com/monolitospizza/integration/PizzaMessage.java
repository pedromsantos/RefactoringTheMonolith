package com.monolitospizza.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matt Stine
 */
public class PizzaMessage {

    private String size;
    private String crust;
    private String sauce;
    private List<ToppingMessage> toppingMessages = new ArrayList<>();

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public List<ToppingMessage> getToppingMessages() {
        return toppingMessages;
    }

    public void addToppingMessage(ToppingMessage toppingMessage) {
        this.toppingMessages.add(toppingMessage);
    }
}
