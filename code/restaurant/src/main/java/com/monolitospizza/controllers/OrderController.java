package com.monolitospizza.controllers;

import com.monolitospizza.services.MenuService;
import org.springframework.ui.ModelMap;

/**
 * @author Matt Stine
 */
public class OrderController {

    private MenuService menuService;

    public OrderController(MenuService menuService) {
        this.menuService = menuService;
    }

    public String startNewPizza(ModelMap modelMap) {
        modelMap.addAttribute("basePizzaMenuOptions", menuService.loadBasePizzaMenuOptions());

        return "chooseBaseOptions";
    }
}
