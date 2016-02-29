package com.monolitospizza.acl;

import com.monolitospizza.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * TrackingServiceAdaptor - adaptor as defined in "Migrating to Cloud-Native Application Architectures," p. 31.
 *
 * @author Matt Stine
 */
@Component
public class TrackingServiceAdaptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final RestTemplate restTemplate;

    @Autowired
    public TrackingServiceAdaptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendStatusUpdate(Long orderId, String newOrderStatus) {
        logger.info("Sending order status update of {} for order #{} to Store Tracking Application", newOrderStatus, orderId);
        ResponseEntity<UpdateOrderStatusResponse> response = restTemplate.exchange("http://store-tracking/order/{orderId}?orderStatus={orderStatus}", HttpMethod.PUT, new HttpEntity<String>(""), UpdateOrderStatusResponse.class, orderId, newOrderStatus);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            logger.info("Order status update successful!");
        } else {
            logger.warn("Order status update failed!");
        }
    }
}
