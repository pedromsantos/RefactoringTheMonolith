package com.monolitospizza.repositories;

import com.monolitospizza.RestaurantSiteApplication;
import com.monolitospizza.model.Size;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantSiteApplication.class)
@IntegrationTest
public class SizeRepositoryTest {

    @Autowired
    private SizeRepository sizeRepository;

    @Test
    public void canSaveASize() {
        Size size = new Size("Small", BigDecimal.valueOf(8.99));
        size = sizeRepository.save(size);
        assertThat(size.getId(), is(notNullValue()));
    }

    @Test
    public void canFindByName() {
        Size size = sizeRepository.findOneByName("Large");
        assertThat(size, is(notNullValue()));
    }
}
