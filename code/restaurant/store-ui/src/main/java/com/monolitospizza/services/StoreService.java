package com.monolitospizza.services;


import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Matt Stine
 */
@Service
public class StoreService {

    private final String storeApplicationAddress;
    private final RestTemplate restTemplate;

    @Autowired
    public StoreService(@Value("${monolitos.storeApplicationAddress}") String storeApplicationAddress) {
        this.storeApplicationAddress = storeApplicationAddress;
        restTemplate = new RestTemplate();
    }

    public List<Order> ordersForStore() {
        ParameterizedTypeReference<List<Order>> responseType = new ParameterizedTypeReference<List<Order>>() {
        };
        return restTemplate.exchange(storeApplicationAddress + "/orders", HttpMethod.GET, null, responseType).getBody();
    }

    public Order orderDetails(Long orderId) {
        return restTemplate.getForObject(storeApplicationAddress + "/order/{orderId}", Order.class, orderId);
    }

    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        restTemplate.put(storeApplicationAddress + "/order/{orderId}?orderStatus={orderStatus}", null, orderId, orderStatus);
    }
}
