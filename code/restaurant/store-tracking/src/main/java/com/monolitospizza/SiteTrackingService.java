package com.monolitospizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Matt Stine
 */
@Service
public class SiteTrackingService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String siteTrackingApplicationAddress;

    @Autowired
    public SiteTrackingService(@Value("${monolitos.siteTrackingApplicationAddress}") String siteTrackingApplicationAddress) {
        this.siteTrackingApplicationAddress = siteTrackingApplicationAddress;
    }

    public boolean updateOrderStatus(Long orderId, String orderStatus) {
        RestTemplate restTemplate = new RestTemplate();
        logger.info("Sending order status update of {} for order #{} to Site Tracking Application", orderStatus, orderId);
        ResponseEntity<UpdateOrderStatusResponse> response = restTemplate.exchange(siteTrackingApplicationAddress + "/order/{orderId}?orderStatus={orderStatus}", HttpMethod.PUT, new HttpEntity<String>(""), UpdateOrderStatusResponse.class, orderId, orderStatus);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            logger.info("Order status update successful!");
            return true;
        } else {
            logger.info("Order status update failed!");
            return false;
        }
    }
}
