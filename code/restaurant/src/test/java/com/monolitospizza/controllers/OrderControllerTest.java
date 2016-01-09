package com.monolitospizza.controllers;

import com.monolitospizza.model.Crust;
import com.monolitospizza.model.Sauce;
import com.monolitospizza.model.Size;
import com.monolitospizza.services.BasePizzaMenuOptions;
import com.monolitospizza.services.MenuService;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareModelMap;

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

    @Test
    public void shouldLoadBaseOptionsForNewPizza() {
        MenuService mockMenuService = mock(MenuService.class);
        BasePizzaMenuOptions basePizzaMenuOptions = new BasePizzaMenuOptions(Arrays.asList(new Size("Large", BigDecimal.ZERO)),
                Arrays.asList(new Crust("Thin")),
                Arrays.asList(new Sauce("Normal")));
        when(mockMenuService.loadBasePizzaMenuOptions())
                .thenReturn(basePizzaMenuOptions);
        OrderController orderController = new OrderController(mockMenuService);
        ModelMap modelMap = new ModelMap();

        String view = orderController.startNewPizza(modelMap);

        verify(mockMenuService).loadBasePizzaMenuOptions();
        assertThat(view, is(equalTo("chooseBaseOptions")));
        assertThat(modelMap.get("basePizzaMenuOptions"), is(equalTo(basePizzaMenuOptions)));
    }

}
