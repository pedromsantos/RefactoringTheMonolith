package com.monolitospizza.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Matt Stine
 */
@Component
public class RoutingKeyResolver {

    private final String incomingOrderQueueNamePrefix;
    private final String incomingOrderQueueNameSuffix;

    @Autowired
    public RoutingKeyResolver(@Value("${monolitos.incomingOrderQueueNamePrefix}") String incomingOrderQueueNamePrefix,
                              @Value("${monolitos.incomingOrderQueueNameSuffix}") String incomingOrderQueueNameSuffix) {
        this.incomingOrderQueueNamePrefix = incomingOrderQueueNamePrefix;
        this.incomingOrderQueueNameSuffix = incomingOrderQueueNameSuffix;
    }

    public String getRoutingKeyFor(Long storeId) {
        return incomingOrderQueueNamePrefix + storeId + incomingOrderQueueNameSuffix;
    }
}
