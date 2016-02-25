package com.monolitospizza.services;

import com.monolitospizza.model.Order;
import com.monolitospizza.model.Store;
import com.monolitospizza.repositories.OrderRepository;
import com.monolitospizza.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Matt Stine
 */
@Service
public class StoreService {

    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository,
                        OrderRepository orderRepository) {
        this.storeRepository = storeRepository;
        this.orderRepository = orderRepository;
    }

    public List<Order> ordersForStore(long storeId) {
        Store store = storeRepository.findOne(storeId);
        return orderRepository.findAllByStore(store);

    }
}
