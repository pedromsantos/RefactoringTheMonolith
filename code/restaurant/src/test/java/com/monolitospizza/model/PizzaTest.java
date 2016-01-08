package com.monolitospizza.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Matt Stine
 */
public class PizzaTest {

    private Topping sausage;

    @Before
    public void setUp() throws Exception {
        sausage = new Topping("Sausage", BigDecimal.valueOf(0.5));
    }

    @Test
    public void newPizzaHasTwoHalves() {
        Pizza pizza = new Pizza();

        assertThat(pizza.getLeftHalf(), notNullValue());
        assertThat(pizza.getRightHalf(), notNullValue());
    }

    @Test
    public void newPizzaHasDefaultSizeAndCrustAndSauce() {
        Pizza pizza = new Pizza();

        assertThat(Size.LARGE, equalTo(pizza.getSize()));
        assertThat(Crust.THIN, equalTo(pizza.getCrust()));
        assertThat(Sauce.NORMAL, equalTo(pizza.getSauce()));
    }

    @Test
    public void canMakeSmallPizza() {
        Pizza pizza = new Pizza(Size.SMALL);

        assertThat(Size.SMALL, equalTo(pizza.getSize()));
        assertThat(Crust.THIN, equalTo(pizza.getCrust()));
        assertThat(Sauce.NORMAL, equalTo(pizza.getSauce()));
    }

    @Test
    public void canMakeMediumPizza() {
        Pizza pizza = new Pizza(Size.MEDIUM);

        assertThat(Size.MEDIUM, equalTo(pizza.getSize()));
        assertThat(Crust.THIN, equalTo(pizza.getCrust()));
        assertThat(Sauce.NORMAL, equalTo(pizza.getSauce()));
    }

    @Test
    public void canMakeDeepDishPizza() {
        Pizza pizza = new Pizza(Crust.DEEP_DISH);

        assertThat(Size.LARGE, equalTo(pizza.getSize()));
        assertThat(Crust.DEEP_DISH, equalTo(pizza.getCrust()));
        assertThat(Sauce.NORMAL, equalTo(pizza.getSauce()));
    }

    @Test
    public void canMakeHandTossedPizza() {
        Pizza pizza = new Pizza(Crust.HAND_TOSSED);

        assertThat(Size.LARGE, equalTo(pizza.getSize()));
        assertThat(Crust.HAND_TOSSED, equalTo(pizza.getCrust()));
        assertThat(Sauce.NORMAL, equalTo(pizza.getSauce()));
    }

    @Test
    public void canMakeLightSaucePizza() {
        Pizza pizza = new Pizza(Sauce.LIGHT);

        assertThat(Size.LARGE, equalTo(pizza.getSize()));
        assertThat(Crust.THIN, equalTo(pizza.getCrust()));
        assertThat(Sauce.LIGHT, equalTo(pizza.getSauce()));
    }

    @Test
    public void canMakeNoSaucePizza() {
        Pizza pizza = new Pizza(Sauce.NONE);

        assertThat(Size.LARGE, equalTo(pizza.getSize()));
        assertThat(Crust.THIN, equalTo(pizza.getCrust()));
        assertThat(Sauce.NONE, equalTo(pizza.getSauce()));
    }

    @Test
    public void canMakeExtraSaucePizza() {
        Pizza pizza = new Pizza(Sauce.EXTRA);

        assertThat(Size.LARGE, equalTo(pizza.getSize()));
        assertThat(Crust.THIN, equalTo(pizza.getCrust()));
        assertThat(Sauce.EXTRA, equalTo(pizza.getSauce()));
    }

    @Test
    public void canSetSizeAndCrust() {
        Pizza pizza = new Pizza(Size.SMALL, Crust.HAND_TOSSED);

        assertThat(Size.SMALL, equalTo(pizza.getSize()));
        assertThat(Crust.HAND_TOSSED, equalTo(pizza.getCrust()));
        assertThat(Sauce.NORMAL, equalTo(pizza.getSauce()));
    }

    @Test
    public void canSetSizeAndSauce() {
        Pizza pizza = new Pizza(Size.SMALL, Sauce.LIGHT);

        assertThat(Size.SMALL, equalTo(pizza.getSize()));
        assertThat(Crust.THIN, equalTo(pizza.getCrust()));
        assertThat(Sauce.LIGHT, equalTo(pizza.getSauce()));
    }

    @Test
    public void canSetCrustAndSauce() {
        Pizza pizza = new Pizza(Crust.HAND_TOSSED, Sauce.LIGHT);

        assertThat(Size.LARGE, equalTo(pizza.getSize()));
        assertThat(Crust.HAND_TOSSED, equalTo(pizza.getCrust()));
        assertThat(Sauce.LIGHT, equalTo(pizza.getSauce()));
    }

    @Test
    public void canSetSizeAndCrustAndSauce() {
        Pizza pizza = new Pizza(Size.SMALL, Crust.HAND_TOSSED, Sauce.LIGHT);

        assertThat(Size.SMALL, equalTo(pizza.getSize()));
        assertThat(Crust.HAND_TOSSED, equalTo(pizza.getCrust()));
        assertThat(Sauce.LIGHT, equalTo(pizza.getSauce()));
    }

    @Test
    public void canAddLeftTopping() {
        Pizza pizza = new Pizza();

        pizza.addLeftTopping(sausage);

        assertTrue(pizza.getLeftHalf().getToppings().contains(sausage));
    }

    @Test
    public void canRemoveLeftTopping() {
        Pizza pizza = new Pizza();

        pizza.addLeftTopping(sausage);
        pizza.removeLeftTopping(sausage);

        assertFalse(pizza.getLeftHalf().getToppings().contains(sausage));
    }

    @Test
    public void canAddRightTopping() {
        Pizza pizza = new Pizza();

        pizza.addRightTopping(sausage);

        assertTrue(pizza.getRightHalf().getToppings().contains(sausage));
    }

    @Test
    public void canRemoveRightTopping() {
        Pizza pizza = new Pizza();

        pizza.addRightTopping(sausage);
        pizza.removeRightTopping(sausage);

        assertFalse(pizza.getRightHalf().getToppings().contains(sausage));
    }

    @Test
    public void canAddWholeTopping() {
        Pizza pizza = new Pizza();

        pizza.addTopping(sausage);

        assertTrue(pizza.getLeftHalf().getToppings().contains(sausage));
        assertTrue(pizza.getRightHalf().getToppings().contains(sausage));
    }

    @Test
    public void canRemoveWholeTopping() {
        Pizza pizza = new Pizza();

        pizza.addTopping(sausage);
        pizza.removeTopping(sausage);

        assertFalse(pizza.getLeftHalf().getToppings().contains(sausage));
        assertFalse(pizza.getRightHalf().getToppings().contains(sausage));
    }

    @Test
    public void defaultPizzaHasDefaultPrice() {
        Pizza pizza = new Pizza();

        assertThat(Size.LARGE.getPrice(), equalTo(pizza.getPrice()));
    }

    @Test
    public void pizzaWithToppingsIncludesThemInPrice() {
        Pizza pizza = new Pizza();

        pizza.addLeftTopping(sausage);
        pizza.addRightTopping(new Topping("Onion", BigDecimal.valueOf(0.5)));

        BigDecimal expectedPrice =
                Size.LARGE.getPrice()
                        .add(sausage.getPrice())
                        .add(new Topping("Onion", BigDecimal.valueOf(0.5)).getPrice());

        assertThat(expectedPrice, equalTo(pizza.getPrice()));
    }

}
