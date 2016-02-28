package com.monolitospizza.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * OrderServiceAdaptor - adaptor as defined in "Migrating to Cloud-Native Application Architectures," p. 31.
 *
 * @author Matt Stine
 */
@RestController
public class OrderServiceAdaptor {

    private final OrderServiceFacade orderServiceFacade;

    @Autowired
    public OrderServiceAdaptor(OrderServiceFacade orderServiceFacade) {
        this.orderServiceFacade = orderServiceFacade;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/order/{orderId}")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(@PathVariable("orderId") Long orderId,
                                                                       @RequestParam("orderStatus") String orderStatus) {
        if (orderServiceFacade.updateOrderStatus(orderId, orderStatus)) {
            return new ResponseEntity<>(new UpdateOrderStatusResponse(orderId, orderStatus, "SUCCESS"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new UpdateOrderStatusResponse(orderId, orderStatus, "FAILED"), HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
