package com.monolitospizza.services;

import com.monolitospizza.integration.DispatchGateway;
import com.monolitospizza.integration.DispatchOrderResponse;
import com.monolitospizza.model.*;
import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.OrderRepository;
import com.monolitospizza.repositories.PizzaRepository;
import com.monolitospizza.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Matt Stine
 */
@Service
@Profile("site")
public class OrderService {
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PizzaRepository pizzaRepository;
//    private final DispatchService dispatchService;
    private final DispatchGateway dispatchGateway;

    @Autowired
    public OrderService(StoreRepository storeRepository,
                        OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        PizzaRepository pizzaRepository,
                        DispatchGateway dispatchGateway) {
        this.storeRepository = storeRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.pizzaRepository = pizzaRepository;
//        this.dispatchService = dispatchService;
        this.dispatchGateway = dispatchGateway;
    }

    public Order startNewPickupOrder(long customerId) {
        Order order = new Order(OrderType.FOR_PICKUP, customerRepository.findOne(customerId), storeRepository.findOne(10000L));
        orderRepository.save(order);
        return order;
    }

    public Order startNewDeliveryOrder(long customerId) {
        Order order = new Order(OrderType.FOR_DELIVERY, customerRepository.findOne(customerId), storeRepository.findOne(10000L));
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

    public Order updateOrder(Order currentOrder) {
        return orderRepository.save(currentOrder);
    }

    public void submitOrder(long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.setStatus(OrderStatus.SUBMITTED);
        orderRepository.save(order);
        DispatchOrderResponse response = dispatchGateway.dispatchOrder(order);
        if (response.getErrorMessage() != null) {
            order.setStatus(OrderStatus.DISPATCHED);
        } else {
            order.setStatus(OrderStatus.RECEIVED);
        }
        orderRepository.save(order);
    }
}
