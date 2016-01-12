package com.monolitospizza.services;

import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderType;
import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matt Stine
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
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
}
