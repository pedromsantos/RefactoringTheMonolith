package com.monolitospizza.controllers;

import com.monolitospizza.helpers.ChooseToppingsViewHelper;
import com.monolitospizza.helpers.ChooseToppingsViewHelperLocation;
import com.monolitospizza.model.*;
import com.monolitospizza.services.BasePizzaMenuOptions;
import com.monolitospizza.services.MenuService;
import com.monolitospizza.services.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Matt Stine
 */
public class OrderControllerTest {

    private MenuService mockMenuService;
    private OrderController orderController;
    private OrderService mockOrderService;
    private Customer customer;
    private Pizza currentPizza;
    private ModelMap modelMap;
    private Order currentOrder;
    private Pizza expectedUpdatePizza;
    private Iterable<Topping> expectedToppings;
    private HttpSession mockHttpSession;

    @Before
    public void setUp() throws Exception {
        mockOrderService = mock(OrderService.class);
        mockMenuService = mock(MenuService.class);

        orderController = new OrderController(mockOrderService, mockMenuService);
        customer = new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187");
        currentPizza = new Pizza(1L, new Size("Large", BigDecimal.ZERO),
                new Crust("Thin"),
                new Sauce("Normal"));
        modelMap = new ModelMap();

        currentPizza.setOrder(new Order(1L,
                OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"),
                new Store()));

        currentOrder = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"),
                new Store());
        when(mockOrderService.loadOrder(1L))
                .thenReturn(currentOrder);

        expectedToppings = Arrays.asList(new Topping("Sausage", BigDecimal.ZERO),
                new Topping("Onion", BigDecimal.ZERO),
                new Topping("Bell Pepper", BigDecimal.ZERO));
        when(mockMenuService.loadToppingOptions())
                .thenReturn(expectedToppings);

        expectedUpdatePizza = new Pizza(new Size("Large", BigDecimal.ZERO),
                new Crust("Thin"),
                new Sauce("Normal"));
        expectedUpdatePizza.setOrder(currentOrder);
        mockHttpSession = mock(HttpSession.class);
    }

    @Test
    public void startsANewPickupOrder() {
        Order order = new Order(1L, OrderType.FOR_PICKUP, customer, new Store());
        when(mockOrderService.startNewPickupOrder(1L)).thenReturn(order);

        String view = orderController.startNewPickupOrder(1L, modelMap, mockHttpSession);

        assertThat(view, is(equalTo("order")));
        assertThat(modelMap.get("currentOrder"), is(equalTo(order)));

        verify(mockHttpSession).setAttribute("currentOrder", 1L);
    }

    @Test
    public void startsANewDeliveryOrder() {
        customer.setAddress(new Address("2187 Jakku Ave.", "Jakku", "CA", "92187"));
        Order order = new Order(1L, OrderType.FOR_DELIVERY, customer, new Store());
        when(mockOrderService.startNewDeliveryOrder(1L)).thenReturn(order);

        String view = orderController.startNewDeliveryOrder(1L, modelMap, mockHttpSession);

        assertThat(view, is(equalTo("order")));
        assertThat(modelMap.get("currentOrder"), is(equalTo(order)));

        verify(mockHttpSession).setAttribute("currentOrder", 1L);
    }

    @Test
    public void shouldLoadBaseOptionsForNewPizza() {
        BasePizzaMenuOptions basePizzaMenuOptions = new BasePizzaMenuOptions(Arrays.asList(new Size("Large", BigDecimal.ZERO)),
                Arrays.asList(new Crust("Thin")),
                Arrays.asList(new Sauce("Normal")));
        Pizza defaultPizzaConfiguration = new Pizza(new Size("Large", BigDecimal.ZERO),
                new Crust("Thin"),
                new Sauce("Normal"));
        Order order = new Order(1L, OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"),
                new Store());

        when(mockMenuService.loadBasePizzaMenuOptions())
                .thenReturn(basePizzaMenuOptions);
        when(mockMenuService.loadDefaultPizzaConfiguration())
                .thenReturn(defaultPizzaConfiguration);
        when(mockOrderService.loadOrder(1L))
                .thenReturn(order);

        Order updatedOrder = new Order(1L, OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"),
                new Store());
        Pizza savedPizza = new Pizza(1L, new Size("Large", BigDecimal.ZERO),
                new Crust("Thin"),
                new Sauce("Normal"));
        updatedOrder.addPizza(savedPizza);

        when(mockOrderService.updateOrder(updatedOrder))
                .thenReturn(updatedOrder);

        HttpSession mockSession = mock(HttpSession.class);

        when(mockSession.getAttribute("currentOrder"))
                .thenReturn(1L);

        String view = orderController.startNewPizza(mockSession, modelMap);

        verify(mockMenuService).loadBasePizzaMenuOptions();
        verify(mockMenuService).loadDefaultPizzaConfiguration();
        verify(mockOrderService).loadOrder(1L);
        verify(mockOrderService).updateOrder(order);
        assertThat(view, is(equalTo("chooseBaseOptions")));
        assertThat(modelMap.get("basePizzaMenuOptions"), is(equalTo(basePizzaMenuOptions)));

        defaultPizzaConfiguration.setOrder(order);
        assertThat(modelMap.get("currentPizza"), is(equalTo(savedPizza)));
        assertThat(((Pizza)modelMap.get("currentPizza")).getId(), is(equalTo(1L)));
    }

    @Test
    public void shouldUpdatePizzaAndLoadToppingOptions() {
        when(mockOrderService.loadPizza(1L))
                .thenReturn(currentPizza);

        Pizza commandObject = new Pizza(1L, new Size("Small", BigDecimal.ZERO),
                new Crust("Hand Tossed"),
                new Sauce("None"));
        commandObject.setOrder(currentOrder);

        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(
                expectedToppings,
                commandObject
        );

        String view = orderController.updatePizzaAndChooseToppings(commandObject, modelMap);

        verify(mockOrderService).loadPizza(1L);
        verify(mockOrderService).updatePizza(commandObject);
        verify(mockMenuService).loadToppingOptions();
        assertThat(modelMap.get("helper"), is(equalTo(helper)));
        assertThat(view, is(equalTo("chooseToppings")));
    }

    @Test
    public void shouldAddToppingToWholePizzaAndLoadToppingOptions() {
        when(mockOrderService.loadPizza(1L))
                .thenReturn(currentPizza);
        Topping sausage = new Topping("Sausage", BigDecimal.ZERO);
        when(mockMenuService.loadTopping(1L))
                .thenReturn(sausage);
        expectedUpdatePizza.addTopping(sausage);
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(
                expectedToppings,
                expectedUpdatePizza
        );

        String view = orderController.addTopping(1L, 1L, ChooseToppingsViewHelperLocation.WHOLE, modelMap);

        verify(mockOrderService).loadPizza(1L);
        verify(mockMenuService).loadTopping(1L);
        verify(mockOrderService).updatePizza(expectedUpdatePizza);
        verify(mockMenuService).loadToppingOptions();
        assertThat(modelMap.get("helper"), is(equalTo(helper)));
        assertThat(view, is(equalTo("chooseToppings")));
    }

    @Test
    public void shouldAddToppingToLeftHalfAndLoadToppingOptions() {
        when(mockOrderService.loadPizza(1L))
                .thenReturn(currentPizza);
        Topping sausage = new Topping("Sausage", BigDecimal.ZERO);
        when(mockMenuService.loadTopping(1L))
                .thenReturn(sausage);
        expectedUpdatePizza.addLeftTopping(sausage);
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(
                expectedToppings,
                expectedUpdatePizza
        );

        String view = orderController.addTopping(1L, 1L, ChooseToppingsViewHelperLocation.LEFT, modelMap);

        verify(mockOrderService).loadPizza(1L);
        verify(mockMenuService).loadTopping(1L);
        verify(mockOrderService).updatePizza(expectedUpdatePizza);
        verify(mockMenuService).loadToppingOptions();
        assertThat(modelMap.get("helper"), is(equalTo(helper)));
        assertThat(view, is(equalTo("chooseToppings")));
    }

    @Test
    public void shouldAddToppingToRightHalfAndLoadToppingOptions() {
        when(mockOrderService.loadPizza(1L))
                .thenReturn(currentPizza);
        Topping sausage = new Topping("Sausage", BigDecimal.ZERO);
        when(mockMenuService.loadTopping(1L))
                .thenReturn(sausage);
        expectedUpdatePizza.addRightTopping(sausage);
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(
                expectedToppings,
                expectedUpdatePizza
        );

        String view = orderController.addTopping(1L, 1L, ChooseToppingsViewHelperLocation.RIGHT, modelMap);

        verify(mockOrderService).loadPizza(1L);
        verify(mockMenuService).loadTopping(1L);
        verify(mockOrderService).updatePizza(expectedUpdatePizza);
        verify(mockMenuService).loadToppingOptions();
        assertThat(modelMap.get("helper"), is(equalTo(helper)));
        assertThat(view, is(equalTo("chooseToppings")));
    }

    @Test
    public void shouldRemoveToppingAndLoadToppingOptions() {
        Topping sausage = new Topping(1L, "Sausage", BigDecimal.ZERO);
        currentPizza.addRightTopping(sausage);
        when(mockOrderService.loadPizza(1L))
                .thenReturn(currentPizza);
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(
                expectedToppings,
                expectedUpdatePizza
        );

        String view = orderController.removeTopping(1L, 1L, ChooseToppingsViewHelperLocation.RIGHT, modelMap);

        verify(mockOrderService).loadPizza(1L);
        verify(mockOrderService).updatePizza(expectedUpdatePizza);
        verify(mockMenuService).loadToppingOptions();
        assertThat(modelMap.get("helper"), is(equalTo(helper)));
        assertThat(view, is(equalTo("chooseToppings")));
    }

    @Test
    public void shouldLoadOrderWhenContinuing() {
        HttpSession mockHttpSession = mock(HttpSession.class);
        when(mockHttpSession.getAttribute("currentOrder"))
                .thenReturn(1L);

        when(mockOrderService.loadOrder(1L))
                .thenReturn(currentOrder);

        String view = orderController.continueOrder(mockHttpSession, modelMap);

        verify(mockOrderService).loadOrder(1L);
        assertThat(modelMap.get("currentOrder"), is(equalTo(currentOrder)));
        assertThat(view, is(equalTo("order")));
    }

    @Test
    public void shouldCheckOut() {
        HttpSession mockHttpSession = mock(HttpSession.class);
        when(mockHttpSession.getAttribute("currentOrder"))
                .thenReturn(1L);

        when(mockOrderService.loadOrder(1L))
                .thenReturn(currentOrder);

        String view = orderController.checkOut(mockHttpSession, modelMap);

        verify(mockOrderService).loadOrder(1L);
        verify(mockHttpSession).removeAttribute("currentOrder");
        assertThat(modelMap.get("currentOrder"), is(equalTo(currentOrder)));
        assertThat(view, is(equalTo("checkOut")));
    }
}
