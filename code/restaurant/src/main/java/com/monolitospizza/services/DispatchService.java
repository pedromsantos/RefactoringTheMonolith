package com.monolitospizza.services;

import com.monolitospizza.messaging.DispatchOrderResponse;
import com.monolitospizza.model.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Matt Stine
 */
@Service
@Profile("site")
public class DispatchService {

    @Autowired
    AmqpTemplate amqpTemplate;

    public DispatchOrderResponse dispatchOrder(Order order) {
        Long storeId = order.getStore().getId();
        return (DispatchOrderResponse) amqpTemplate.convertSendAndReceive("monolitos.store." + storeId + ".incomingOrders", order);
    }

}
