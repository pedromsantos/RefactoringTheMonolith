package com.monolitospizza.services;

import com.monolitospizza.model.Crust;
import com.monolitospizza.model.Sauce;
import com.monolitospizza.model.Size;
import com.monolitospizza.repositories.CrustRepository;
import com.monolitospizza.repositories.SauceRepository;
import com.monolitospizza.repositories.SizeRepository;

/**
 * @author Matt Stine
 */
public class MenuService {
    private final SizeRepository sizeRepository;
    private final CrustRepository crustRepository;
    private final SauceRepository sauceRepository;

    public MenuService(SizeRepository sizeRepository, CrustRepository crustRepository, SauceRepository sauceRepository) {
        this.sizeRepository = sizeRepository;
        this.crustRepository = crustRepository;
        this.sauceRepository = sauceRepository;
    }

    public BasePizzaMenuOptions loadBasePizzaMenuOptions() {
        Iterable<Size> sizes = sizeRepository.findAll();
        Iterable<Crust> crusts = crustRepository.findAll();
        Iterable<Sauce> sauces = sauceRepository.findAll();

        return new BasePizzaMenuOptions(sizes, crusts, sauces);
    }
}
