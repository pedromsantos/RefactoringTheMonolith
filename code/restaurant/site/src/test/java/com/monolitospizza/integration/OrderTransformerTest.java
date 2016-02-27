package com.monolitospizza.integration;

import com.monolitospizza.model.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class OrderTransformerTest {

    @Test
    public void shouldTransformSiteOrder() {
        Customer customer = new Customer("Rey", "rey@theresistance.com", "+1(999)999-9999");

        Address address = new Address(
                "875 Howard St.",
                "San Francisco",
                "CA",
                "94101"
        );
        customer.setAddress(address);

        Order order = new Order(OrderType.FOR_DELIVERY, customer, new Store());

        Pizza firstPizza = new Pizza(new Size("Large", BigDecimal.valueOf(12.99)),
                new Crust("Thin"),
                new Sauce("Normal"));
        firstPizza.addLeftTopping(new Topping("Beef", BigDecimal.valueOf(0.5)));
        firstPizza.addRightTopping(new Topping("Bacon", BigDecimal.valueOf(0.5)));

        Pizza secondPizza = new Pizza(new Size("Large", BigDecimal.valueOf(12.99)),
                new Crust("Thin"),
                new Sauce("Normal"));
        secondPizza.addTopping(new Topping("Pepperoni", BigDecimal.valueOf(0.5)));
        secondPizza.addTopping(new Topping("Mushroom", BigDecimal.valueOf(0.5)));

        BigDecimal expectedPrice = firstPizza
                .getPrice().add(secondPizza.getPrice());

        order.addPizza(firstPizza);
        order.addPizza(secondPizza);

        OrderTransformer transformer = new OrderTransformer();

        OrderMessage orderMessage = transformer.transform(order);
        assertThat(orderMessage.getType(), is(equalTo(OrderType.FOR_DELIVERY.getDisplayName())));
        assertThat(orderMessage.getPrice(), is(equalTo(BigDecimal.valueOf(28.98))));

        CustomerMessage customerMessage = orderMessage.getCustomerMessage();
        assertThat(customerMessage.getName(), is(equalTo(customer.getName())));
        assertThat(customerMessage.getEmail(), is(equalTo(customer.getEmail())));
        assertThat(customerMessage.getPhone(), is(equalTo(customer.getPhone())));
        assertThat(customerMessage.getStreetAddress(), is(equalTo(customer.getAddress().getStreetAddress())));
        assertThat(customerMessage.getCity(), is(equalTo(customer.getAddress().getCity())));
        assertThat(customerMessage.getState(), is(equalTo(customer.getAddress().getState())));
        assertThat(customerMessage.getPostalCode(), is(equalTo(customer.getAddress().getPostalCode())));

        List<PizzaMessage> pizzaMessages = orderMessage.getPizzaMessages();
        PizzaMessage pizzaMessage = pizzaMessages.get(0);
        assertThat(pizzaMessage.getSize(), is(equalTo("Large")));
        assertThat(pizzaMessage.getCrust(), is(equalTo("Thin")));
        assertThat(pizzaMessage.getSauce(), is(equalTo("Normal")));

        List<ToppingMessage> toppingMessages = pizzaMessage.getToppingMessages();
        assertThat(toppingMessages.size(), is(equalTo(2)));
        ToppingMessage toppingMessage = toppingMessages.get(0);
        assertThat(toppingMessage.getName(), is(equalTo("Beef")));
        assertThat(toppingMessage.getLocation(), is(equalTo("Left Half")));
        toppingMessage = toppingMessages.get(1);
        assertThat(toppingMessage.getName(), is(equalTo("Bacon")));
        assertThat(toppingMessage.getLocation(), is(equalTo("Right Half")));

        pizzaMessage = pizzaMessages.get(1);
        assertThat(pizzaMessage.getSize(), is(equalTo("Large")));
        assertThat(pizzaMessage.getCrust(), is(equalTo("Thin")));
        assertThat(pizzaMessage.getSauce(), is(equalTo("Normal")));

        toppingMessages = pizzaMessage.getToppingMessages();
        assertThat(toppingMessages.size(), is(equalTo(2)));
        toppingMessage = toppingMessages.get(0);
        assertThat(toppingMessage.getName(), is(equalTo("Pepperoni")));
        assertThat(toppingMessage.getLocation(), is(equalTo("Whole Pizza")));
        toppingMessage = toppingMessages.get(1);
        assertThat(toppingMessage.getName(), is(equalTo("Mushroom")));
        assertThat(toppingMessage.getLocation(), is(equalTo("Whole Pizza")));
    }
}
