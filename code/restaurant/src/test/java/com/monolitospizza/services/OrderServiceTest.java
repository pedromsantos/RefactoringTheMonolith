package com.monolitospizza.services;

import com.monolitospizza.model.Customer;
import com.monolitospizza.model.Order;
import com.monolitospizza.model.OrderType;
import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.OrderRepository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Matt Stine
 */
public class OrderServiceTest {

    @Test
    public void startsANewPickupOrder() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        CustomerRepository customerRepository = mock(CustomerRepository.class);

        Customer customer = new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187");
        Order expectedResult = new Order(OrderType.FOR_PICKUP, customer);

        when(customerRepository.findOne(1L)).thenReturn(customer);

        OrderService orderService = new OrderService(orderRepository, customerRepository);

        Order result = orderService.startNewPickupOrder(1L);

        assertThat(result, is(equalTo(expectedResult)));
        verify(customerRepository).findOne(1L);
        verify(orderRepository).save(expectedResult);
    }
}
