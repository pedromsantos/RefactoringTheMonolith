package com.monolitospizza.acl;

import com.monolitospizza.model.OrderStatus;
import org.springframework.stereotype.Component;

/**
 * TrackingServiceTranslator - translator as defined in "Migrating to Cloud-Native Application Architectures," p. 31.
 *
 * @author Matt Stine
 */
@Component
public class TrackingServiceTranslator {

    public String mapOrderStatus(OrderStatus orderStatus) {
        return orderStatus.getDisplayName();
    }
}
