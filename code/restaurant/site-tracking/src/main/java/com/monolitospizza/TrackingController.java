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

    private final OrderService orderService;

    @Autowired
    public TrackingController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/order/{orderId}")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(@PathVariable("orderId") Long orderId,
                                                                       @RequestParam("orderStatus") String orderStatus) {
        if (orderService.updateOrderStatus(orderId, orderStatus)) {
            return new ResponseEntity<>(new UpdateOrderStatusResponse(orderId, orderStatus, "SUCCESS"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new UpdateOrderStatusResponse(orderId, orderStatus, "FAILED"), HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
