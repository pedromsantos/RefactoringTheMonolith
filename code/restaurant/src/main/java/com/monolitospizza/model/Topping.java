package com.monolitospizza.model;

import java.math.BigDecimal;

/**
 * @author Matt Stine
 */
public enum Topping {
    SAUSAGE(new BigDecimal(1.00)),
    PEPPERONI(new BigDecimal(1.00)),
    BEEF(new BigDecimal(1.00)),
    HAM(new BigDecimal(1.00)),
    BACON(new BigDecimal(1.00)),
    ONION(new BigDecimal(1.00)),
    BELL_PEPPER(new BigDecimal(1.00)),
    MUSHROOM(new BigDecimal(1.00)),
    GREEN_OLIVE(new BigDecimal(1.00)),
    BLACK_OLIVE(new BigDecimal(1.00));

    Topping(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }
}
