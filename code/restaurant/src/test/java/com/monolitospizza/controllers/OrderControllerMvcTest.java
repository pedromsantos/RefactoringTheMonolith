package com.monolitospizza.controllers;

import com.monolitospizza.RestaurantApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = RestaurantApplication.class)
public class OrderControllerMvcTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void startsANewPickupOrder() throws Exception {
        this.mockMvc.perform(get("/pickupOrder?customerId=10000"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("currentOrder"))
                .andExpect(view().name("order"));
    }

    @Test
    public void startsANewDeliveryOrder() throws Exception {
        this.mockMvc.perform(get("/deliveryOrder?customerId=10000"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("currentOrder"))
                .andExpect(view().name("order"));
    }

    @Test
    public void startsANewPizza() throws Exception {
        this.mockMvc.perform(get("/addPizza?orderId=10000"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("basePizzaMenuOptions"))
                .andExpect(model().attributeExists("currentPizza"))
                .andExpect(view().name("chooseBaseOptions"));
    }

    @Test
    public void shouldUpdatePizzaAndLoadToppingOptions() throws Exception {
        this.mockMvc.perform(post("/chooseToppings")
                .param("size", "10000")
                .param("crust", "10000")
                .param("sauce", "10000")
                .param("order", "10000"))
                .andExpect(status().isOk())
                .andExpect(view().name("chooseToppings"));

    }
}
