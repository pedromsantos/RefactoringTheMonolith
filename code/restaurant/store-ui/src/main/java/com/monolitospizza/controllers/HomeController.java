package com.monolitospizza.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Matt Stine
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String loadCustomerForHome() {
        return "redirect:/manageOrders";
    }
}
