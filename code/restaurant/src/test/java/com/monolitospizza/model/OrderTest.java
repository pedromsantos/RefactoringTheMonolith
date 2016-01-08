package com.monolitospizza.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class OrderTest {

    private Order order;

    @Before
    public void setUp() throws Exception {
        order = new Order(OrderType.FOR_DELIVERY);
    }

    @Test
    public void orderCanBeForDelivery() {
        assertThat(order.getType(), equalTo(OrderType.FOR_DELIVERY));
    }

    @Test
    public void orderCanBeForPickup() {
        order = new Order(OrderType.FOR_PICKUP);

        assertThat(order.getType(), equalTo(OrderType.FOR_PICKUP));
    }

    @Test
    public void emptyOrderHasNoPrice() {
        assertThat(BigDecimal.ZERO, equalTo(order.getPrice()));
    }

    @Test
    public void orderWithOnePizzaHasPriceOfPizza() {
        Pizza pizza = new Pizza();

        order.addPizza(pizza);

        assertThat(pizza.getPrice(), equalTo(order.getPrice()));
    }

    @Test
    public void orderWithTwoPizzasHasSumOfTheirPrices() {
        Pizza firstPizza = new Pizza(Size.MEDIUM);
        firstPizza.addTopping(Topping.BEEF);
        firstPizza.addTopping(Topping.BACON);

        Pizza secondPizza = new Pizza(Size.SMALL);
        secondPizza.addTopping(Topping.PEPPERONI);
        secondPizza.addTopping(Topping.MUSHROOM);

        BigDecimal expectedPrice = firstPizza
                .getPrice().add(secondPizza.getPrice());

        order.addPizza(firstPizza);
        order.addPizza(secondPizza);

        assertThat(order.getPrice(), equalTo(expectedPrice));
    }
}
