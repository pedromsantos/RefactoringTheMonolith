package com.monolitospizza.services;

import com.monolitospizza.integration.DispatchOrderResponse;
import com.monolitospizza.integration.OrderMessage;
import com.monolitospizza.model.*;
import com.monolitospizza.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Matt Stine
 */
@Service
public class StoreService {

    private final OrderRepository orderRepository;

    @Autowired
    public StoreService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> ordersForStore() {
        return orderRepository.findAll();
    }

    public DispatchOrderResponse receiveIncomingOrder(OrderMessage orderMessage) {
        try {
            orderRepository.save(transformOrderMessageToOrder(orderMessage));
        } catch (Exception e) {
            return new DispatchOrderResponse("Error from Store #" + orderMessage.getStoreId() + ": " + e.getMessage());
        }
        return new DispatchOrderResponse();
    }

    public Order orderDetails(Long orderId) {
        return orderRepository.findOne(orderId);
    }

    private Order transformOrderMessageToOrder(OrderMessage orderMessage) {
        Order order = new Order();
        order.setStoreId(orderMessage.getStoreId());
        order.setId(orderMessage.getId());
        order.setPrice(orderMessage.getPrice());
        order.setType(orderMessage.getType());
        order.setOrderStatus(OrderStatus.RECEIVED);

        Customer customer = new Customer();
        order.setCustomer(customer);
        customer.setName(orderMessage.getCustomerMessage().getName());
        customer.setEmail(orderMessage.getCustomerMessage().getEmail());
        customer.setPhone(orderMessage.getCustomerMessage().getPhone());
        customer.setStreetAddress(orderMessage.getCustomerMessage().getStreetAddress());
        customer.setCity(orderMessage.getCustomerMessage().getCity());
        customer.setState(orderMessage.getCustomerMessage().getState());
        customer.setPostalCode(orderMessage.getCustomerMessage().getPostalCode());

        orderMessage.getPizzaMessages().forEach(pizzaMessage -> {
            Pizza pizza = new Pizza();
            pizza.setSize(pizzaMessage.getSize());
            pizza.setCrust(pizzaMessage.getCrust());
            pizza.setSauce(pizzaMessage.getSauce());
            order.addPizza(pizza);
            pizza.setOrder(order);

            pizzaMessage.getToppingMessages().forEach(toppingMessage -> {
                Topping topping = new Topping();
                topping.setName(toppingMessage.getName());
                topping.setLocation(toppingMessage.getLocation());
                pizza.addTopping(topping);
                topping.setPizza(pizza);
            });
        });

        return order;
    }
}
