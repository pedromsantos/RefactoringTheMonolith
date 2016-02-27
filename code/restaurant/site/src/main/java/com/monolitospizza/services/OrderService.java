package com.monolitospizza.services;

import com.monolitospizza.integration.DispatchGateway;
import com.monolitospizza.integration.DispatchOrderResponse;
import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderStatus;
import com.monolitospizza.model.OrderType;
import com.monolitospizza.model.Pizza;
import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.OrderRepository;
import com.monolitospizza.repositories.PizzaRepository;
import com.monolitospizza.repositories.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matt Stine
 */
@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PizzaRepository pizzaRepository;
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
            logger.error(response.getErrorMessage());
            order.setStatus(OrderStatus.DISPATCHED);
        } else {
            order.setStatus(OrderStatus.RECEIVED);
        }
        orderRepository.save(order);
    }
}
