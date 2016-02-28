package com.monolitospizza.controllers;

import com.monolitospizza.model.OrderStatus;
import com.monolitospizza.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Matt Stine
 */
@Controller
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping("/manageOrders")
    public String manageOrders(ModelMap modelMap) {
        modelMap.addAttribute("orders", storeService.ordersForStore());
        return "manageOrders";
    }

    @RequestMapping("/orderDetails")
    public String orderDetails(@RequestParam("orderId") Long orderId,
                               ModelMap modelMap) {
        modelMap.addAttribute("currentOrder", storeService.orderDetails(orderId));
        return "orderDetails";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateOrderStatus")
    public String updateOrderStatus(@RequestParam("orderId") Long orderId,
                                    @RequestParam("orderStatus") OrderStatus orderStatus) {
        storeService.updateOrderStatus(orderId, orderStatus);
        return "redirect:/manageOrders";
    }
}
