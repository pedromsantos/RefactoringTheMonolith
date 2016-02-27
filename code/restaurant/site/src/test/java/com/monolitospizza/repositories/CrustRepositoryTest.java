package com.monolitospizza.repositories;

import com.monolitospizza.RestaurantApplication;
import com.monolitospizza.model.Crust;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantApplication.class)
@IntegrationTest
public class CrustRepositoryTest {

    @Autowired
    private CrustRepository crustRepository;

    @Test
    public void canSaveACrust() {
        Crust crust = new Crust("Thin");
        crust = crustRepository.save(crust);
        assertThat(crust.getId(), is(notNullValue()));
    }

    @Test
    public void canFindByName() {
        Crust crust = crustRepository.findOneByName("Thin");
        assertThat(crust, is(equalTo(new Crust("Thin"))));
    }
}
