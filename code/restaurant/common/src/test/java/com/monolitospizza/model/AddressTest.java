package com.monolitospizza.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class AddressTest {

    @Test
    public void anAddressMustBeComplete() {
        Address address = new Address(
                "875 Howard St.",
                "San Francisco",
                "CA",
                "94101"
        );

        assertThat(address.getStreetAddress(), is(equalTo("875 Howard St.")));
        assertThat(address.getCity(), is(equalTo("San Francisco")));
        assertThat(address.getState(), is(equalTo("CA")));
        assertThat(address.getPostalCode(), is(equalTo("94101")));
    }
}
