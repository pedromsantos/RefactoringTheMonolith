package com.monolitospizza.services;

import com.monolitospizza.integration.DispatchOrderResponse;
import com.monolitospizza.integration.OrderMessage;
import com.monolitospizza.model.Order;
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
@Profile("store")
public class StoreService {

    private final String storeId;

    private final OrderRepository orderRepository;

    @Autowired
    public StoreService(OrderRepository orderRepository,
                        @Value("${monolitos.storeId}") String storeId) {
        this.orderRepository = orderRepository;
        this.storeId = storeId;
    }

    public List<Order> ordersForStore() {
        return orderRepository.findAll();
    }

    public DispatchOrderResponse receiveIncomingOrder(OrderMessage orderMessage) {
        try {
            System.out.println("Save the order!");
//            order.setStatus(OrderStatus.RECEIVED);
//            orderRepository.save(order);
        } catch (Exception e) {
            return new DispatchOrderResponse("Error from Store #" + orderMessage.getStoreId() + ": " + e.getMessage());
        }
        return new DispatchOrderResponse();
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Order orderDetails(Long orderId) {
        return orderRepository.findOne(orderId);
    }
}
