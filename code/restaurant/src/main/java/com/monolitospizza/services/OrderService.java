package com.monolitospizza.services;

import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderType;
import com.monolitospizza.model.Pizza;
import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.OrderRepository;
import com.monolitospizza.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matt Stine
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PizzaRepository pizzaRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, PizzaRepository pizzaRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.pizzaRepository = pizzaRepository;
    }

    public Order startNewPickupOrder(long customerId) {
        Order order = new Order(OrderType.FOR_PICKUP, customerRepository.findOne(customerId));
        orderRepository.save(order);
        return order;
    }

    public Order startNewDeliveryOrder(long customerId) {
        Order order = new Order(OrderType.FOR_DELIVERY, customerRepository.findOne(customerId));
        orderRepository.save(order);
        return order;
    }

    public Order loadOrder(long orderId) {
        return orderRepository.findOne(orderId);
    }

    public void updatePizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    public Pizza loadPizza(Long pizzaId) {
        return pizzaRepository.findOne(pizzaId);
    }

    public void updateOrder(Order currentOrder) {
        orderRepository.save(currentOrder);
    }
}
