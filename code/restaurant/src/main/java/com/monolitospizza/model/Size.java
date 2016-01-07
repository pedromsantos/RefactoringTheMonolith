package com.monolitospizza.model;

import java.math.BigDecimal;

/**
 * @author Matt Stine
 */
public enum Size {
    SMALL(BigDecimal.valueOf(8.99)),
    MEDIUM(BigDecimal.valueOf(10.99)),
    LARGE(BigDecimal.valueOf(12.99));

    Size(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }
}
