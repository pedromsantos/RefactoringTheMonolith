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

    @Before
    public void setUp() throws Exception {
        mockOrderService = mock(OrderService.class);
        mockMenuService = mock(MenuService.class);

        orderController = new OrderController(mockOrderService, mockMenuService);
        customer = new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187");
        currentPizza = new Pizza(new Size("Large", BigDecimal.ZERO),
                new Crust("Thin"),
                new Sauce("Normal"));
        modelMap = new ModelMap();

        currentPizza.setOrder(new Order(1L,
                OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187")));

        currentOrder = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"));
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
    }

    @Test
    public void startsANewPickupOrder() {
        Order order = new Order(OrderType.FOR_PICKUP, customer);
        when(mockOrderService.startNewPickupOrder(1L)).thenReturn(order);

        String view = orderController.startNewPickupOrder(1L, modelMap);

        assertThat(view, is(equalTo("order")));
        assertThat(modelMap.get("currentOrder"), is(equalTo(order)));
    }

    @Test
    public void startsANewDeliveryOrder() {
        customer.setAddress(new Address("2187 Jakku Ave.", "Jakku", "CA", "92187"));
        Order order = new Order(OrderType.FOR_DELIVERY, customer);
        when(mockOrderService.startNewDeliveryOrder(1L)).thenReturn(order);

        String view = orderController.startNewDeliveryOrder(1L, modelMap);

        assertThat(view, is(equalTo("order")));
        assertThat(modelMap.get("currentOrder"), is(equalTo(order)));
    }

    @Test
    public void shouldLoadBaseOptionsForNewPizza() {
        BasePizzaMenuOptions basePizzaMenuOptions = new BasePizzaMenuOptions(Arrays.asList(new Size("Large", BigDecimal.ZERO)),
                Arrays.asList(new Crust("Thin")),
                Arrays.asList(new Sauce("Normal")));
        Pizza defaultPizzaConfiguration = new Pizza(new Size("Large", BigDecimal.ZERO),
                new Crust("Thin"),
                new Sauce("Normal"));
        Order order = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"));

        when(mockMenuService.loadBasePizzaMenuOptions())
                .thenReturn(basePizzaMenuOptions);
        when(mockMenuService.loadDefaultPizzaConfiguration())
                .thenReturn(defaultPizzaConfiguration);
        when(mockOrderService.loadOrder(1L))
                .thenReturn(order);

        String view = orderController.startNewPizza(1L, modelMap);

        verify(mockMenuService).loadBasePizzaMenuOptions();
        verify(mockMenuService).loadDefaultPizzaConfiguration();
        verify(mockOrderService).loadOrder(1L);
        assertThat(view, is(equalTo("chooseBaseOptions")));
        assertThat(modelMap.get("basePizzaMenuOptions"), is(equalTo(basePizzaMenuOptions)));

        defaultPizzaConfiguration.setOrder(order);
        assertThat(modelMap.get("currentPizza"), is(equalTo(defaultPizzaConfiguration)));
    }

    @Test
    public void shouldUpdatePizzaAndLoadToppingOptions() {
        ChooseToppingsViewHelper helper = new ChooseToppingsViewHelper(
                expectedToppings,
                currentPizza
        );

        String view = orderController.updatePizzaAndChooseToppings(currentPizza, modelMap);

        verify(mockOrderService).loadOrder(1L);
        verify(mockOrderService).updateOrder(currentOrder);
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
    public void shoudRemoveToppingAndLoadToppingOptions() {
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

}
