package com.monolitospizza.integration;

import com.monolitospizza.model.Order;

/**
 * @author Matt Stine
 */
public class OrderTransformer {
    public OrderMessage transform(Order order) {
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setType(order.getType().getDisplayName());
        orderMessage.setPrice(order.getPrice());

        CustomerMessage customerMessage = new CustomerMessage();
        orderMessage.setCustomerMessage(customerMessage);
        customerMessage.setName(order.getCustomer().getName());
        customerMessage.setEmail(order.getCustomer().getEmail());
        customerMessage.setPhone(order.getCustomer().getPhone());
        customerMessage.setStreetAddress(order.getCustomer().getAddress().getStreetAddress());
        customerMessage.setCity(order.getCustomer().getAddress().getCity());
        customerMessage.setState(order.getCustomer().getAddress().getState());
        customerMessage.setPostalCode(order.getCustomer().getAddress().getPostalCode());

        order.getPizzas().forEach(pizza -> {
            PizzaMessage pizzaMessage = new PizzaMessage();
            orderMessage.addPizzaMessage(pizzaMessage);

            pizzaMessage.setSize(pizza.getSize().getName());
            pizzaMessage.setCrust(pizza.getCrust().getName());
            pizzaMessage.setSauce(pizza.getSauce().getName());

            pizza.getLeftHalf().getToppings().forEach(topping -> {
                ToppingMessage toppingMessage = new ToppingMessage();
                pizzaMessage.addToppingMessage(toppingMessage);
                toppingMessage.setName(topping.getName());
                if (pizza.rightHalfContainsTopping(topping)) {
                    toppingMessage.setLocation("Whole Pizza");
                } else {
                    toppingMessage.setLocation("Left Half");
                }
            });

            pizza.getRightHalf().getToppings().forEach(topping -> {
                if (!pizza.leftHalfContainsTopping(topping)) {
                    ToppingMessage toppingMessage = new ToppingMessage();
                    pizzaMessage.addToppingMessage(toppingMessage);
                    toppingMessage.setName(topping.getName());
                    toppingMessage.setLocation("Right Half");
                }
            });
        });

        return orderMessage;
    }
}
