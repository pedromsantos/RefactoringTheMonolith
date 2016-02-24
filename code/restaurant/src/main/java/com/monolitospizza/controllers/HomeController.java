package com.monolitospizza.controllers;

import com.monolitospizza.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/")
    public String loadCustomerForHome(Principal principal, ModelMap modelMap, HttpSession httpSession) {
        Long currentOrderId = (Long) httpSession.getAttribute("currentOrder");
        if (currentOrderId != null) {
            return "redirect:/continueOrder";
        }

        String email = principal.getName();
        modelMap.addAttribute("currentCustomer", customerRepository.findByEmail(email));
        return "home";
    }
}
