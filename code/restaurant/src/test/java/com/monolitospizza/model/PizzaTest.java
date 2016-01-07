package com.monolitospizza.model;

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

        pizza.addLeftTopping(Topping.SAUSAGE);

        assertTrue(pizza.getLeftHalf().getToppings().contains(Topping.SAUSAGE));
    }

    @Test
    public void canRemoveLeftTopping() {
        Pizza pizza = new Pizza();

        pizza.addLeftTopping(Topping.SAUSAGE);
        pizza.removeLeftTopping(Topping.SAUSAGE);

        assertFalse(pizza.getLeftHalf().getToppings().contains(Topping.SAUSAGE));
    }

    @Test
    public void canAddRightTopping() {
        Pizza pizza = new Pizza();

        pizza.addRightTopping(Topping.SAUSAGE);

        assertTrue(pizza.getRightHalf().getToppings().contains(Topping.SAUSAGE));
    }

    @Test
    public void canRemoveRightTopping() {
        Pizza pizza = new Pizza();

        pizza.addRightTopping(Topping.SAUSAGE);
        pizza.removeRightTopping(Topping.SAUSAGE);

        assertFalse(pizza.getRightHalf().getToppings().contains(Topping.SAUSAGE));
    }

    @Test
    public void defaultPizzaHasDefaultPrice() {
        Pizza pizza = new Pizza();

        assertThat(Size.LARGE.getPrice(), equalTo(pizza.getPrice()));
    }

    @Test
    public void pizzaWithToppingsIncludesThemInPrice() {
        Pizza pizza = new Pizza();

        pizza.addLeftTopping(Topping.SAUSAGE);
        pizza.addRightTopping(Topping.ONION);

        BigDecimal expectedPrice =
                Size.LARGE.getPrice()
                        .add(Topping.SAUSAGE.getPrice())
                        .add(Topping.ONION.getPrice());

        assertThat(expectedPrice, equalTo(pizza.getPrice()));
    }

}
