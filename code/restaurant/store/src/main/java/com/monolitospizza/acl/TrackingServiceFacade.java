package com.monolitospizza.acl;

import com.monolitospizza.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TrackingServiceFacade - facade as defined in "Migrating to Cloud-Native Application Architectures," p. 31.
 *
 * @author Matt Stine
 */
@Service
public class TrackingServiceFacade {

    private final TrackingServiceAdaptor trackingServiceAdaptor;
    private final TrackingServiceTranslator trackingServiceTranslator;

    @Autowired
    public TrackingServiceFacade(TrackingServiceAdaptor trackingServiceAdaptor, TrackingServiceTranslator trackingServiceTranslator) {
        this.trackingServiceAdaptor = trackingServiceAdaptor;
        this.trackingServiceTranslator = trackingServiceTranslator;
    }

    public void updateOrderStatus(Long orderId, OrderStatus newOrderStatus) {
        trackingServiceAdaptor.sendStatusUpdate(orderId,
                trackingServiceTranslator.mapOrderStatus(newOrderStatus));
    }
}
