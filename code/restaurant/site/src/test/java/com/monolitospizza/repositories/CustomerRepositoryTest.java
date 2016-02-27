package com.monolitospizza.repositories;

import com.monolitospizza.RestaurantApplication;
import com.monolitospizza.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantApplication.class)
@IntegrationTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void canSaveACustomer() {
        Customer customer = new Customer("Rey", "customer@theresistance.com", "+1(999)999-9999");
        customer = customerRepository.save(customer);
        assertThat(customer.getId(), is(notNullValue()));
    }


}
