package com.monolitospizza.controllers;

import com.monolitospizza.model.Customer;
import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.StoreRepository;
import com.monolitospizza.services.OrderService;
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

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final OrderService orderService;

    @Autowired
    public HomeController(CustomerRepository customerRepository,
                          StoreRepository storeRepository,
                          OrderService orderService) {
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.orderService = orderService;
    }

    @RequestMapping("/")
    public String loadCustomerForHome(Principal principal, ModelMap modelMap, HttpSession httpSession) {
        Long currentOrderId = (Long) httpSession.getAttribute("currentOrder");
        if (currentOrderId != null) {
            return "redirect:/continueOrder";
        }

        String email = principal.getName();
        Customer currentCustomer = customerRepository.findByEmail(email);
        modelMap.addAttribute("currentCustomer", currentCustomer);
        modelMap.addAttribute("stores", storeRepository.findAll());
        modelMap.addAttribute("currentOrders", orderService.loadCurrentOrdersForCustomer(currentCustomer));
        return "home";
    }
}
