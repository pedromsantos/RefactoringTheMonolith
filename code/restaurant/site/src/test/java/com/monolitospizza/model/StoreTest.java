package com.monolitospizza.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Matt Stine
 */
public class StoreTest {

    @Test
    public void storeHasAnAddress() {
        Address address = new Address("12345 Shield Generator Way", "Starkiller Base", "SB", "12345");
        Store store = new Store(address);

        assertThat(store.getAddress(), is(equalTo(address)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void storeMustHaveAnAddress() {
        new Store(null);
    }
}
