package com.monolitospizza.acl;

import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderStatus;
import com.monolitospizza.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Matt Stine
 */
@RestController
public class UiFacade {

    private final StoreService storeService;

    @Autowired
    public UiFacade(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    public List<Order> ordersForStore() {
        return storeService.ordersForStore();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order/{orderId}")
    public Order orderDetails(@PathVariable("orderId") Long orderId) {
        return storeService.orderDetails(orderId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/order/{orderId}")
    public void updateOrderStatus(@PathVariable("orderId") Long orderId,
                                  @RequestParam("orderStatus") OrderStatus newOrderStatus) {
        storeService.updateOrderStatus(orderId, newOrderStatus);
    }
}
