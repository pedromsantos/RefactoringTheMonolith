package com.monolitospizza.services;

import com.monolitospizza.messaging.DispatchOrderResponse;
import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderStatus;
import com.monolitospizza.model.Store;
import com.monolitospizza.repositories.OrderRepository;
import com.monolitospizza.repositories.StoreRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository,
                        OrderRepository orderRepository,
                        @Value("${monolitos.storeId}") String storeId) {
        this.storeRepository = storeRepository;
        this.orderRepository = orderRepository;
        this.storeId = storeId;
    }

    public List<Order> ordersForStore(long storeId) {
        Store store = storeRepository.findOne(storeId);
        return orderRepository.findAllByStore(store);
    }

    @RabbitListener(queues = "#{incomingOrderQueueName}")
    public DispatchOrderResponse receiveIncomingOrder(Order order) {
        try {
            order.setStatus(OrderStatus.RECEIVED);
            orderRepository.save(order);
        } catch (Exception e) {
            return new DispatchOrderResponse("Error from Store #" + order.getStore().getId() + ": " + e.getMessage());
        }
        return new DispatchOrderResponse();
    }
}
