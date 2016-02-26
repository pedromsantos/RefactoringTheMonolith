package com.monolitospizza.repositories;

import com.monolitospizza.RestaurantApplication;
import com.monolitospizza.model.Customer;
import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderType;
import com.monolitospizza.model.Store;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void canSaveAnOrder() {
        Customer customer = customerRepository.findOne(10000L);
        Store store = storeRepository.findOne(10000L);
        Order order = new Order(OrderType.FOR_PICKUP, customer, store);

        order = orderRepository.save(order);

        assertThat(order.getId(), is(notNullValue()));
    }

}
