package com.monolitospizza.repositories;

import com.monolitospizza.RestaurantApplication;
import com.monolitospizza.model.Sauce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantApplication.class)
@IntegrationTest
public class SauceRepositoryTest {

    @Autowired
    private SauceRepository sauceRepository;

    @Test
    public void canSaveASauce() {
        Sauce sauce = new Sauce("Normal");
        sauce = sauceRepository.save(sauce);
        assertThat(sauce.getId(), is(notNullValue()));
    }

    @Test
    public void canFindByName() {
        Sauce sauce = sauceRepository.findOneByName("Normal");
        assertThat(sauce, is(equalTo(new Sauce("Normal"))));
    }
}
