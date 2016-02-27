package com.monolitospizza.model;

import java.io.Serializable;

/**
 * @author Matt Stine
 */
public enum OrderType implements Serializable {
    FOR_PICKUP("Pickup"), FOR_DELIVERY("Delivery");

    private String displayName;

    OrderType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
