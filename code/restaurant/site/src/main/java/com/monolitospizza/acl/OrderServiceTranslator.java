package com.monolitospizza.acl;

import com.monolitospizza.model.OrderStatus;
import org.springframework.stereotype.Component;

/**
 * OrderServiceTranslator - translator as defined in "Migrating to Cloud-Native Application Architectures," p. 31.
 *
 * @author Matt Stine
 */
@Component
public class OrderServiceTranslator {

    public OrderStatus mapOrderStatus(String orderStatus) {
        if ("Ready for Pickup".equals(orderStatus) || "Out for Delivery".equals(orderStatus)) {
            return OrderStatus.valueOf("COMPLETED");
        } else {
            throw new IllegalArgumentException("Order Status \"" + orderStatus + "\" is unknown and cannot be mapped.");
        }
    }
}
