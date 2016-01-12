package com.monolitospizza.repositories;

import com.monolitospizza.model.Pizza;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Matt Stine
 */
public interface PizzaRepository extends PagingAndSortingRepository<Pizza, Long> {
}
