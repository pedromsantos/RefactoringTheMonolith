package com.monolitospizza.services;

import com.monolitospizza.integration.DispatchGateway;
import com.monolitospizza.messaging.DispatchOrderResponse;
import com.monolitospizza.model.*;
import com.monolitospizza.repositories.CustomerRepository;
import com.monolitospizza.repositories.OrderRepository;
import com.monolitospizza.repositories.PizzaRepository;
import com.monolitospizza.repositories.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Matt Stine
 */
public class OrderServiceTest {

    private OrderRepository mockOrderRepository;
    private CustomerRepository mockCustomerRepository;
    private OrderService orderService;
    private PizzaRepository mockPizzaRepository;
    private StoreRepository mockStoreRepository;
//    private DispatchService mockDispatchService;
    private DispatchGateway mockDispatchGateway;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockOrderRepository = mock(OrderRepository.class);
        mockCustomerRepository = mock(CustomerRepository.class);
        mockPizzaRepository = mock(PizzaRepository.class);
        mockStoreRepository = mock(StoreRepository.class);
//        mockDispatchService = mock(DispatchService.class);
        mockDispatchGateway = mock(DispatchGateway.class);
        orderService = new OrderService(mockStoreRepository,
                mockOrderRepository, mockCustomerRepository, mockPizzaRepository, mockDispatchGateway);
    }

    @Test
    public void startsANewPickupOrder() {
        Customer customer = new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187");
        Store store = new Store(10000L, new Address("2187 Jakku Ave.", "Jakku", "CA", "92187"));
        Order expectedResult = new Order(OrderType.FOR_PICKUP, customer, store);

        when(mockStoreRepository.findOne(10000L)).thenReturn(store);
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
        Store store = new Store(10000L, new Address("2187 Jakku Ave.", "Jakku", "CA", "92187"));
        Order expectedResult = new Order(OrderType.FOR_DELIVERY, customer, store);

        when(mockStoreRepository.findOne(10000L)).thenReturn(store);
        when(mockCustomerRepository.findOne(1L)).thenReturn(customer);

        Order result = orderService.startNewDeliveryOrder(1L);

        assertThat(result, is(equalTo(expectedResult)));
        verify(mockCustomerRepository).findOne(1L);
        verify(mockOrderRepository).save(expectedResult);
    }

    @Test
    public void loadsAnOrderByItsId() {
        Order expectedOrder = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"), new Store());
        when(mockOrderRepository.findOne(1L))
                .thenReturn(expectedOrder);

        Order actualOrder = orderService.loadOrder(1L);
        assertThat(actualOrder, is(equalTo(expectedOrder)));
    }

    @Test
    public void loadsAPizzaByItsId() {
        Pizza expectedPizza = new Pizza(new Size("Large", BigDecimal.ZERO),
                new Crust("Hand Tossed"),
                new Sauce("Normal"));
        when(mockPizzaRepository.findOne(1L))
                .thenReturn(expectedPizza);

        Pizza actualPizza = orderService.loadPizza(1L);
        assertThat(actualPizza, is(equalTo(expectedPizza)));
    }

    @Test
    public void updatesPizza() {
        Pizza pizza = new Pizza(new Size("Large", BigDecimal.ZERO),
                new Crust("Thin"),
                new Sauce("Normal"));
        Order order = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"), new Store());
        pizza.setOrder(order);

        orderService.updatePizza(pizza);

        verify(mockPizzaRepository).save(pizza);
    }

    @Test
    public void updatesOrder() {
        Order order = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"), new Store());

        orderService.updateOrder(order);

        verify(mockOrderRepository).save(order);
    }

    @Captor
    ArgumentCaptor<Order> orderCaptor;

    @Test
    public void submitsOrder() {
        Order order = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"), new Store());

        Order submittedOrder = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"), new Store());
        submittedOrder.setStatus(OrderStatus.SUBMITTED);

        Order receivedOrder = new Order(OrderType.FOR_PICKUP,
                new Customer("Finn", "fn2187@firstorder.net", "+1(999)999-2187"), new Store());
        receivedOrder.setStatus(OrderStatus.RECEIVED);

        when(mockOrderRepository.findOne(1L))
                .thenReturn(order);

//        when(mockDispatchService.dispatchOrder(submittedOrder))
//                .thenReturn(new DispatchOrderResponse());

        orderService.submitOrder(1L);

        //TODO: make this test verify the actual arguments
        verify(mockOrderRepository, times(2)).save(order);
    }
}
