package com.monolitospizza.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class HalfTest {

    @Test
    public void emptyHalfHasNoPrice() {
        Half half = new Half();

        assertThat(BigDecimal.ZERO, equalTo(half.getPrice()));
    }

    @Test
    public void halfWithToppingsKnowsItsPrice() {
        Half half = new Half();

        half.addTopping(new Topping("Sausage", BigDecimal.valueOf(0.5)));
        half.addTopping(new Topping("Onion", BigDecimal.valueOf(0.5)));
        half.addTopping(new Topping("Bell Pepper", BigDecimal.valueOf(0.5)));

        assertThat(BigDecimal.valueOf(1.5), equalTo(half.getPrice()));
    }
}
