package com.monolitospizza.repositories;

import com.monolitospizza.RestaurantApplication;
import com.monolitospizza.model.Crust;
import com.monolitospizza.model.Pizza;
import com.monolitospizza.model.Sauce;
import com.monolitospizza.model.Size;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Matt Stine
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantApplication.class)
@IntegrationTest
public class PizzaRepositoryTest {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private CrustRepository crustRepository;

    @Autowired
    private SauceRepository sauceRepository;

    @Test
    public void shouldSaveRelationshipsWithBaseOptions() {
        Size size = sizeRepository.findOneByName("Large");
        Crust crust = crustRepository.findOneByName("Thin");
        Sauce sauce = sauceRepository.findOneByName("Normal");


        Pizza pizza = new Pizza(size, crust, sauce);

        Pizza result = pizzaRepository.save(pizza);

        Pizza loaded = pizzaRepository.findOne(result.getId());

        assertEquals(pizza, loaded);
    }
}
