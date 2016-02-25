package com.monolitospizza.controllers;

import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author Matt Stine
 */
@Controller
public class HomeController {

    private static final String MODE_SITE = "SITE";
    private static final String MODE_STORE = "STORE";

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    StoreRepository storeRepository;

    @Value("${monolitos.mode}")
    String mode;

    @RequestMapping("/")
    public String loadCustomerForHome(Principal principal, ModelMap modelMap, HttpSession httpSession) {
        if (mode.equals(MODE_SITE)) {
            Long currentOrderId = (Long) httpSession.getAttribute("currentOrder");
            if (currentOrderId != null) {
                return "redirect:/continueOrder";
            }

            String email = principal.getName();
            modelMap.addAttribute("currentCustomer", customerRepository.findByEmail(email));
            modelMap.addAttribute("stores", storeRepository.findAll());
            return "home";
        } else {
            return "redirect:/manageOrders";
        }
    }
}
