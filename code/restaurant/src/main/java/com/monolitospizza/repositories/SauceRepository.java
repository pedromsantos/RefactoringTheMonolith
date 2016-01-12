package com.monolitospizza.repositories;

import com.monolitospizza.model.Sauce;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Matt Stine
 */
public interface SauceRepository extends PagingAndSortingRepository<Sauce, Long> {
    Sauce findOneByName(String name);
}
