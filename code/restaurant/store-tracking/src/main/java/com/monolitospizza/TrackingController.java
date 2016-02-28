package com.monolitospizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Matt Stine
 */
@RestController
public class TrackingController {

    private final SiteTrackingService siteTrackingService;

    @Autowired
    public TrackingController(SiteTrackingService siteTrackingService) {
        this.siteTrackingService = siteTrackingService;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/order/{orderId}")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(@PathVariable("orderId") Long orderId,
                                                                       @RequestParam("orderStatus") String orderStatus) {
        if (siteTrackingService.updateOrderStatus(orderId, orderStatus)) {
            return new ResponseEntity<>(new UpdateOrderStatusResponse(orderId, orderStatus, "SUCCESS"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new UpdateOrderStatusResponse(orderId, orderStatus, "FAILED"), HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
