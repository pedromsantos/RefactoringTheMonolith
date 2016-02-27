package com.monolitospizza.repositories;

import com.monolitospizza.model.Topping;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Matt Stine
 */
public interface ToppingRepository extends PagingAndSortingRepository<Topping, Long> {
}
