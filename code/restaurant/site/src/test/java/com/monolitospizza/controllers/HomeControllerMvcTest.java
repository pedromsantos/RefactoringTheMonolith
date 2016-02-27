package com.monolitospizza.controllers;

import com.monolitospizza.RestaurantSiteApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Matt Stine
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@IntegrationTest
@SpringApplicationConfiguration(classes = RestaurantSiteApplication.class)
@ActiveProfiles("site")
public class HomeControllerMvcTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void loadsCustomerForHome() throws Exception {
        this.mockMvc.perform(get("/")
                .principal(() -> "rey@theresistance.com"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("currentCustomer"))
                .andExpect(view().name("home"));
    }
}
