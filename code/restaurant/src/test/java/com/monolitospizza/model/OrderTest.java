package com.monolitospizza.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class OrderTest {

    @Test
    public void emptyOrderHasNoPrice() {
        Order order = new Order();

        assertThat(BigDecimal.ZERO, equalTo(order.getPrice()));
    }

    @Test
    public void orderWithOnePizzaHasPriceOfPizza() {
        Order order = new Order();
        Pizza pizza = new Pizza();

        order.addPizza(pizza);

        assertThat(pizza.getPrice(), equalTo(order.getPrice()));
    }

    @Test
    public void orderWithTwoPizzasHasSumOfTheirPrices() {
        Order order = new Order();

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
