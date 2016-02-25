package com.monolitospizza.model;

/**
 * @author Matt Stine
 */
public enum OrderType {
    FOR_PICKUP("Pickup"), FOR_DELIVERY("Delivery");

    private String displayName;

    OrderType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
