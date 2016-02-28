package com.monolitospizza.acl;

import com.monolitospizza.model.Order;
import com.monolitospizza.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderServiceFacade - facade as defined in "Migrating to Cloud-Native Application Architectures," p. 31.
 *
 * @author Matt Stine
 */
@Service
public class OrderServiceFacade {

    private final OrderService orderService;
    private final OrderServiceTranslator orderServiceTranslator;

    @Autowired
    public OrderServiceFacade(OrderService orderService, OrderServiceTranslator orderServiceTranslator) {
        this.orderService = orderService;
        this.orderServiceTranslator = orderServiceTranslator;
    }

    public boolean updateOrderStatus(Long orderId, String orderStatus) {
        try {
            Order order = orderService.loadOrder(orderId);
            order.setStatus(orderServiceTranslator.mapOrderStatus(orderStatus));
            orderService.updateOrder(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
