package com.monolitospizza.services;

import com.monolitospizza.model.Crust;
import com.monolitospizza.model.Sauce;
import com.monolitospizza.model.Size;
import com.monolitospizza.repositories.CrustRepository;
import com.monolitospizza.repositories.SauceRepository;
import com.monolitospizza.repositories.SizeRepository;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Matt Stine
 */
public class MenuServiceTest {

    @Test
    public void shouldLoadBasePizzaMenuOptions() {
        CrustRepository mockCrustRepository = mock(CrustRepository.class);
        SauceRepository mockSauceRepository = mock(SauceRepository.class);
        SizeRepository mockSizeRepository = mock(SizeRepository.class);

        when(mockSizeRepository.findAll())
                .thenReturn(Arrays.asList(new Size("Large", BigDecimal.ZERO)));

        when(mockCrustRepository.findAll())
                .thenReturn(Arrays.asList(new Crust("Thin")));

        when(mockSauceRepository.findAll())
                .thenReturn(Arrays.asList(new Sauce("Normal")));

        MenuService menuService = new MenuService(mockSizeRepository,
                mockCrustRepository,
                mockSauceRepository);

        BasePizzaMenuOptions baseMenuOptions = menuService.loadBasePizzaMenuOptions();

        assertThat(baseMenuOptions.getSizes().iterator().hasNext(), is(true));
        assertThat(baseMenuOptions.getCrusts().iterator().hasNext(), is(true));
        assertThat(baseMenuOptions.getSauces().iterator().hasNext(), is(true));

    }
}
