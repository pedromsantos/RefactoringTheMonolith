package com.monolitospizza.controllers;

import com.monolitospizza.services.MenuService;
import com.monolitospizza.services.OrderService;
import org.springframework.ui.ModelMap;

/**
 * @author Matt Stine
 */
public class OrderController {

    private final OrderService orderService;
    private final MenuService menuService;

    public OrderController(OrderService orderService, MenuService menuService) {
        this.orderService = orderService;
        this.menuService = menuService;
    }

    public String startNewPizza(ModelMap modelMap) {
        modelMap.addAttribute("basePizzaMenuOptions", menuService.loadBasePizzaMenuOptions());
        return "chooseBaseOptions";
    }

    public String startNewPickupOrder(long customerId, ModelMap modelMap) {
        modelMap.addAttribute("currentOrder", orderService.startNewPickupOrder(customerId));
        return "addAPizza";
    }

    public String startNewDeliveryOrder(long customerId, ModelMap modelMap) {
        modelMap.addAttribute("currentOrder", orderService.startNewDeliveryOrder(customerId));
        return "addAPizza";
    }
}
