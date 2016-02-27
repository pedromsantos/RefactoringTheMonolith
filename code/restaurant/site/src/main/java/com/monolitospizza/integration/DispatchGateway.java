package com.monolitospizza.integration;

import com.monolitospizza.model.Order;

/**
 * @author Matt Stine
 */
public interface DispatchGateway {

    DispatchOrderResponse dispatchOrder(Order order);
}
