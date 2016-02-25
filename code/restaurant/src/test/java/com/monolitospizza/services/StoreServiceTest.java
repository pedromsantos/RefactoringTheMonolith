package com.monolitospizza.services;

import com.monolitospizza.model.*;
import com.monolitospizza.repositories.OrderRepository;
import com.monolitospizza.repositories.StoreRepository;
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
    private StoreRepository mockStoreRepository;

    @Before
    public void setUp() throws Exception {
        mockOrderRepository = mock(OrderRepository.class);
        mockStoreRepository = mock(StoreRepository.class);

        storeService = new StoreService(mockStoreRepository, mockOrderRepository);
    }

    @Test
    public void loadsOrdersByStoreId() throws Exception {
        Customer customer = new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187");
        Store store = new Store(10000L, new Address("2187 Jakku Ave.", "Jakku", "CA", "92187"));
        Order order = new Order(OrderType.FOR_PICKUP, customer, store);

        List<Order> orders = Arrays.asList(order);

        when(mockStoreRepository.findOne(10000L))
                .thenReturn(store);
        when(mockOrderRepository.findAllByStore(store))
                .thenReturn(orders);

        assertThat(storeService.ordersForStore(10000L), is(equalTo(orders)));
    }
}
