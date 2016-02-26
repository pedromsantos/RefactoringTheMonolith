package com.monolitospizza.model;

import java.io.Serializable;

/**
 * @author Matt Stine
 */
public enum OrderStatus implements Serializable {
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
