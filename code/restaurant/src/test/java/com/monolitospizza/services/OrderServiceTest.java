package com.monolitospizza.services;

import com.monolitospizza.model.*;
import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.OrderRepository;
import com.monolitospizza.repositories.PizzaRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Matt Stine
 */
public class OrderServiceTest {

    private OrderRepository mockOrderRepository;
    private CustomerRepository mockCustomerRepository;
    private OrderService orderService;
    private PizzaRepository mockPizzaRepository;

    @Before
    public void setUp() throws Exception {
        mockOrderRepository = mock(OrderRepository.class);
        mockCustomerRepository = mock(CustomerRepository.class);
        mockPizzaRepository = mock(PizzaRepository.class);
        orderService = new OrderService(mockOrderRepository, mockCustomerRepository, mockPizzaRepository);
    }

    @Test
    public void startsANewPickupOrder() {
        Customer customer = new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187");
        Order expectedResult = new Order(OrderType.FOR_PICKUP, customer);

        when(mockCustomerRepository.findOne(1L)).thenReturn(customer);

        Order result = orderService.startNewPickupOrder(1L);

        assertThat(result, is(equalTo(expectedResult)));
        verify(mockCustomerRepository).findOne(1L);
        verify(mockOrderRepository).save(expectedResult);
    }

    @Test
    public void startsANewDeliveryOrder() {
        Customer customer = new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187");
        customer.setAddress(new Address("2187 Jakku Ave.", "Jakku", "CA", "92187"));
        Order expectedResult = new Order(OrderType.FOR_DELIVERY, customer);

        when(mockCustomerRepository.findOne(1L)).thenReturn(customer);

        Order result = orderService.startNewDeliveryOrder(1L);

        assertThat(result, is(equalTo(expectedResult)));
        verify(mockCustomerRepository).findOne(1L);
        verify(mockOrderRepository).save(expectedResult);
    }

    @Test
    public void loadsAnOrderByItsId() {
        Order expectedOrder = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"));
        when(mockOrderRepository.findOne(1L))
                .thenReturn(expectedOrder);

        Order actualOrder = orderService.loadOrder(1L);
        assertThat(actualOrder, is(equalTo(expectedOrder)));
    }

    @Test
    public void updatesPizza() {
        Pizza pizza = new Pizza(new Size("Large", BigDecimal.ZERO),
                new Crust("Thin"),
                new Sauce("Normal"));
        Order order = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"));
        pizza.setOrder(order);

        orderService.updatePizza(pizza);

        verify(mockPizzaRepository).save(pizza);
    }
}
