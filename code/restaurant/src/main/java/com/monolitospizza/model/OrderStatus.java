package com.monolitospizza.model;

/**
 * @author Matt Stine
 */
public enum OrderStatus {
    STARTED("Started"),
    SUBMITTED("Submitted"),
    DISPATCHED("Dispatched"),
    RECEIVED("Received"),
    COMPLETED("Completed");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
