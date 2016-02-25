package com.monolitospizza.controllers;

import com.monolitospizza.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Matt Stine
 */
@Controller
public class StoreController {

    private final long storeId;
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService,
                           @Value("${monolitos.storeId}") long storeId) {
        this.storeService = storeService;
        this.storeId = storeId;
    }

    @RequestMapping("/manageOrders")
    public String manageOrders(ModelMap modelMap) {
        modelMap.addAttribute("orders", storeService.ordersForStore(storeId));
        return "manageOrders";
    }
}
