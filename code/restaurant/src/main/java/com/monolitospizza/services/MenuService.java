package com.monolitospizza.services;

import com.monolitospizza.model.Crust;
import com.monolitospizza.model.Pizza;
import com.monolitospizza.model.Sauce;
import com.monolitospizza.model.Size;
import com.monolitospizza.repositories.CrustRepository;
import com.monolitospizza.repositories.SauceRepository;
import com.monolitospizza.repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matt Stine
 */
@Service
public class MenuService {
    private final SizeRepository sizeRepository;
    private final CrustRepository crustRepository;
    private final SauceRepository sauceRepository;

    @Autowired
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

    public Pizza loadDefaultPizzaConfiguration() {
        return new Pizza(sizeRepository.findOneByName("Large"),
                crustRepository.findOneByName("Thin"),
                sauceRepository.findOneByName("Normal"));
    }
}
