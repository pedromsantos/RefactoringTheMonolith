package com.monolitospizza.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class HalfTest {

    private Topping sausage;
    private Half half;

    @Before
    public void setUp() throws Exception {
        sausage = new Topping("Sausage", BigDecimal.valueOf(0.5));
        half = new Half();
    }

    @Test
    public void emptyHalfHasNoPrice() {

        assertThat(BigDecimal.ZERO, equalTo(half.getPrice()));
    }

    @Test
    public void halfWithToppingsKnowsItsPrice() {
        half.addTopping(sausage);
        half.addTopping(new Topping("Onion", BigDecimal.valueOf(0.5)));
        half.addTopping(new Topping("Bell Pepper", BigDecimal.valueOf(0.5)));

        assertThat(BigDecimal.valueOf(1.5), equalTo(half.getPrice()));
    }

    @Test
    public void halfKnowsIfItContainsTopping() {
        half.addTopping(sausage);

        assertThat(half.containsTopping(sausage), is(true));
    }

    @Test
    public void halfKnowsIfItDoesNotContainTopping() {
        assertThat(half.containsTopping(sausage), is(false));
    }

    @Test
    public void canRemoveToppingById() {
        Topping topping = new Topping(1L, "Topping w/ ID", BigDecimal.ZERO);
        half.addTopping(topping);
        half.removeToppingById(1L);

        assertFalse(half.containsTopping(topping));
    }
}
