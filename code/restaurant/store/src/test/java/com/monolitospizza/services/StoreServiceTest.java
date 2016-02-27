package com.monolitospizza.services;

import com.monolitospizza.model.*;
import com.monolitospizza.repositories.OrderRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Matt Stine
 */
public class StoreServiceTest {

    private OrderRepository mockOrderRepository;
    private StoreService storeService;

    @Before
    public void setUp() throws Exception {
        mockOrderRepository = mock(OrderRepository.class);

        storeService = new StoreService(mockOrderRepository, "10000");
    }

    @Test
    public void loadsOrdersByStoreId() throws Exception {
        Customer customer = new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187");
        Order order = new Order("Pickup", customer);

        List<Order> orders = Arrays.asList(order);

        when(mockOrderRepository.findAll())
                .thenReturn(orders);

        assertThat(storeService.ordersForStore(), is(equalTo(orders)));
    }
}
