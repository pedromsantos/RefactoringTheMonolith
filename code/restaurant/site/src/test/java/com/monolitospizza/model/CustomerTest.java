package com.monolitospizza.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Rey", "customer@theresistance.com", "+1(999)999-9999");
    }

    @Test
    public void customerMustHaveANameAndEmailAndPhone() {
        assertThat(customer.getName(), is(equalTo("Rey")));
        assertThat(customer.getEmail(), is(equalTo("customer@theresistance.com")));
        assertThat(customer.getPhone(), is(equalTo("+1(999)999-9999")));
    }

    @Test
    public void customerCanHaveAnAddress() {
        Address address = new Address(
                "875 Howard St.",
                "San Francisco",
                "CA",
                "94101"
        );

        customer.setAddress(address);

        assertThat(customer.getAddress(), is(equalTo(address)));
    }


}
