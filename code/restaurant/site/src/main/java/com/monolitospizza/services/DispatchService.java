package com.monolitospizza.services;

import com.monolitospizza.integration.DispatchOrderResponse;
import com.monolitospizza.model.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Matt Stine
 */
@Service
@Profile("site")
public class DispatchService {

    private final String incomingOrderQueueNamePrefix;
    private final String incomingOrderQueueNameSuffix;
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public DispatchService(@Value("${monolitos.incomingOrderQueueNamePrefix}") String incomingOrderQueueNamePrefix,
                           @Value("${monolitos.incomingOrderQueueNameSuffix}") String incomingOrderQueueNameSuffix,
                           AmqpTemplate amqpTemplate) {
        this.incomingOrderQueueNamePrefix = incomingOrderQueueNamePrefix;
        this.incomingOrderQueueNameSuffix = incomingOrderQueueNameSuffix;
        this.amqpTemplate = amqpTemplate;
    }

    public DispatchOrderResponse dispatchOrder(Order order) {
        Long storeId = order.getStore().getId();
        return (DispatchOrderResponse) amqpTemplate.convertSendAndReceive(incomingOrderQueueNamePrefix + storeId + incomingOrderQueueNameSuffix, order);
    }

}
