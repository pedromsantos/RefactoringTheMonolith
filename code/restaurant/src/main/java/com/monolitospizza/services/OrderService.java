package com.monolitospizza.services;

import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderType;
import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.OrderRepository;

/**
 * @author Matt Stine
 */
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Order startNewPickupOrder(long customerId) {
        Order order = new Order(OrderType.FOR_PICKUP, customerRepository.findOne(1L));
        orderRepository.save(order);
        return order;
    }
}
