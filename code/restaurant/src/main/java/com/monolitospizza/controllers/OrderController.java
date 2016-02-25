package com.monolitospizza.controllers;

import com.monolitospizza.helpers.ChooseToppingsViewHelper;
import com.monolitospizza.helpers.ChooseToppingsViewHelperLocation;
import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderType;
import com.monolitospizza.model.Pizza;
import com.monolitospizza.model.Topping;
import com.monolitospizza.services.MenuService;
import com.monolitospizza.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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

    @RequestMapping(method = RequestMethod.POST, value = "/newOrder")
    public String startNewOrder(@RequestParam(name = "customerId") long customerId,
                                @RequestParam(name = "orderType") OrderType orderType,
                                ModelMap modelMap,
                                HttpSession session) {
        Long orderId = (Long) session.getAttribute("currentOrder");
        if (orderId != null) {
            return "redirect:/continueOrder";
        } else {
            Order currentOrder;
            if (orderType == OrderType.FOR_PICKUP) {
                currentOrder = orderService.startNewPickupOrder(customerId);
            } else {
                currentOrder = orderService.startNewDeliveryOrder(customerId);
            }
            session.setAttribute("currentOrder", currentOrder.getId());
            modelMap.addAttribute("currentOrder", currentOrder);
            return "order";
        }
    }

    @RequestMapping("/addPizza")
    public String startNewPizza(HttpSession session, ModelMap modelMap) {
        Long orderId = (Long) session.getAttribute("currentOrder");
        if (orderId == null) {
            return "redirect:/";
        } else {
            modelMap.addAttribute("basePizzaMenuOptions", menuService.loadBasePizzaMenuOptions());
            Pizza currentPizza = menuService.loadDefaultPizzaConfiguration();
            Order currentOrder = orderService.loadOrder(orderId);
            currentOrder.addPizza(currentPizza);
            Order savedOrder = orderService.updateOrder(currentOrder);
            modelMap.addAttribute("currentPizza", savedOrder.getNewestPizza());
            return "chooseBaseOptions";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chooseToppings")
    public String updatePizzaAndChooseToppings(@ModelAttribute Pizza commandObject, ModelMap modelMap) {
        Pizza currentPizza = orderService.loadPizza(commandObject.getId());

        currentPizza.setCrust(commandObject.getCrust());
        currentPizza.setSauce(commandObject.getSauce());
        currentPizza.setSize(commandObject.getSize());

        orderService.updatePizza(currentPizza);
        prepareChooseToppingsViewHelper(currentPizza, modelMap);
        return "chooseToppings";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTopping")
    public String addTopping(@RequestParam(name = "id") long pizzaId,
                             @RequestParam(name = "topping") long toppingId,
                             @RequestParam(name = "location") ChooseToppingsViewHelperLocation location,
                             ModelMap modelMap) {
        Pizza currentPizza = orderService.loadPizza(pizzaId);

        Topping topping = menuService.loadTopping(toppingId);
        switch (location) {
            case LEFT:
                currentPizza.addLeftTopping(topping);
                break;
            case RIGHT:
                currentPizza.addRightTopping(topping);
                break;
            case WHOLE:
                currentPizza.addTopping(topping);
                break;
        }
        orderService.updatePizza(currentPizza);

        prepareChooseToppingsViewHelper(currentPizza, modelMap);
        return "chooseToppings";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/removeTopping")
    public String removeTopping(@RequestParam(name = "id") long pizzaId,
                                @RequestParam(name = "topping") long toppingId,
                                @RequestParam(name = "location") ChooseToppingsViewHelperLocation location,
                                ModelMap modelMap) {
        Pizza currentPizza = orderService.loadPizza(pizzaId);

        switch (location) {
            case LEFT:
                currentPizza.removeLeftToppingById(toppingId);
                break;
            case RIGHT:
                currentPizza.removeRightToppingById(toppingId);
                break;
            case WHOLE:
                currentPizza.removeToppingById(toppingId);
                break;
        }

        orderService.updatePizza(currentPizza);

        prepareChooseToppingsViewHelper(currentPizza, modelMap);

        return "chooseToppings";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/continueOrder")
    public String continueOrder(HttpSession httpSession,
                                ModelMap modelMap) {
        Long orderId = (Long) httpSession.getAttribute("currentOrder");
        if (orderId == null) {
            return "redirect:/";
        } else {
            modelMap.addAttribute("currentOrder", orderService.loadOrder(orderId));
            return "order";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkOut")
    public String checkOut(HttpSession session, ModelMap modelMap) {
        Long orderId = (Long) session.getAttribute("currentOrder");
        if (orderId == null) {
            return "redirect:/";
        } else {
            modelMap.addAttribute("currentOrder", orderService.loadOrder(orderId));
            session.removeAttribute("currentOrder");
            return "checkOut";
        }
    }

    private void prepareChooseToppingsViewHelper(Pizza currentPizza, ModelMap modelMap) {
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(
                menuService.loadToppingOptions(),
                currentPizza
        );
        modelMap.addAttribute("helper", helper);
    }
}
