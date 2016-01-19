package com.monolitospizza.controllers;

import com.monolitospizza.helpers.ChooseToppingsViewHelper;
import com.monolitospizza.helpers.ChooseToppingsViewHelperLocation;
import com.monolitospizza.model.Order;
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
        Pizza currentPizza = menuService.loadDefaultPizzaConfiguration();
        currentPizza.setOrder(orderService.loadOrder(orderId));
        modelMap.addAttribute("currentPizza", currentPizza);
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

    @RequestMapping(method = RequestMethod.POST, value = "/chooseToppings")
    public String updatePizzaAndChooseToppings(@ModelAttribute Pizza currentPizza, ModelMap modelMap) {
        Order currentOrder = orderService.loadOrder(currentPizza.getOrder().getId());
        currentOrder.addPizza(currentPizza);

        orderService.updateOrder(currentOrder);
        prepareChooseToppingsViewHelper(currentPizza, modelMap);
        return "chooseToppings";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTopping")
    public String addTopping(@RequestParam(name = "pizza") long pizzaId,
                             @RequestParam(name = "topping") long toppingId,
                             @RequestParam(name = "location") ChooseToppingsViewHelperLocation location,
                             ModelMap modelMap) {
        Pizza currentPizza = orderService.loadPizza(pizzaId);

        Topping topping = menuService.loadTopping(toppingId);
        switch(location) {
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

    private void prepareChooseToppingsViewHelper(Pizza currentPizza, ModelMap modelMap) {
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(
                menuService.loadToppingOptions(),
                currentPizza
        );
        modelMap.addAttribute("helper", helper);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/removeTopping")
    public String removeTopping(@RequestParam(name = "pizza") long pizzaId,
                                @RequestParam(name = "topping") long toppingId,
                                @RequestParam(name = "location") ChooseToppingsViewHelperLocation location,
                                ModelMap modelMap) {
        Pizza currentPizza = orderService.loadPizza(pizzaId);

        switch(location) {
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
    public String continueOrder(@RequestParam(name = "order") long orderId,
                                ModelMap modelMap) {
        modelMap.addAttribute("currentOrder", orderService.loadOrder(orderId));
        return "order";
    }
}
