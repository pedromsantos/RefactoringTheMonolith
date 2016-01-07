package com.monolitospizza.model;

import java.math.BigDecimal;

/**
 * @author Matt Stine
 */
public enum Topping {
    SAUSAGE(new BigDecimal(0.5)),
    PEPPERONI(new BigDecimal(0.5)),
    BEEF(new BigDecimal(0.5)),
    HAM(new BigDecimal(0.5)),
    BACON(new BigDecimal(0.5)),
    ONION(new BigDecimal(0.5)),
    BELL_PEPPER(new BigDecimal(0.5)),
    MUSHROOM(new BigDecimal(0.5)),
    GREEN_OLIVE(new BigDecimal(0.5)),
    BLACK_OLIVE(new BigDecimal(0.5));

    Topping(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }
}
