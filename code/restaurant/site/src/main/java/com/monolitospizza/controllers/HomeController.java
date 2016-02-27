package com.monolitospizza.controllers;

import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Arrays;

/**
 * @author Matt Stine
 */
@Controller
public class HomeController {

    private static final String PROFILE_SITE = "site";
    private static final String PROFILE_STORE = "store";

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final Environment environment;

    @Autowired
    public HomeController(CustomerRepository customerRepository,
                          StoreRepository storeRepository,
                          Environment environment) {
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.environment = environment;
    }

    @RequestMapping("/")
    public String loadCustomerForHome(Principal principal, ModelMap modelMap, HttpSession httpSession) {
        String[] activeProfiles = environment.getActiveProfiles();
        if (Arrays.asList(activeProfiles).contains(PROFILE_SITE)) {
            Long currentOrderId = (Long) httpSession.getAttribute("currentOrder");
            if (currentOrderId != null) {
                return "redirect:/continueOrder";
            }

            String email = principal.getName();
            modelMap.addAttribute("currentCustomer", customerRepository.findByEmail(email));
            modelMap.addAttribute("stores", storeRepository.findAll());
            return "home";
        } else if (Arrays.asList(activeProfiles).contains(PROFILE_STORE)){
            return "redirect:/manageOrders";
        } else {
            throw new IllegalStateException("The application is in an invalid mode and cannot be accessed.");
        }
    }
}
