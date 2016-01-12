package com.monolitospizza.controllers;

import com.monolitospizza.model.Pizza;
import com.monolitospizza.services.MenuService;
import com.monolitospizza.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Matt Stine
 */
@Controller
public class OrderController {

    private final OrderService orderService;
    private final MenuService menuService;

    @Autowired
    public OrderController(OrderService orderService, MenuService menuService) {
        this.orderService = orderService;
        this.menuService = menuService;
    }

    @RequestMapping("/addPizza")
    public String startNewPizza(@RequestParam(name = "orderId") long orderId, ModelMap modelMap) {
        modelMap.addAttribute("basePizzaMenuOptions", menuService.loadBasePizzaMenuOptions());
        modelMap.addAttribute("currentPizza", menuService.loadDefaultPizzaConfiguration());
        return "chooseBaseOptions";
    }

    @RequestMapping("/pickupOrder")
    public String startNewPickupOrder(@RequestParam(name = "customerId") long customerId, ModelMap modelMap) {
        modelMap.addAttribute("currentOrder", orderService.startNewPickupOrder(customerId));
        return "order";
    }

    @RequestMapping("/deliveryOrder")
    public String startNewDeliveryOrder(@RequestParam(name = "customerId") long customerId, ModelMap modelMap) {
        modelMap.addAttribute("currentOrder", orderService.startNewDeliveryOrder(customerId));
        return "order";
    }
}
