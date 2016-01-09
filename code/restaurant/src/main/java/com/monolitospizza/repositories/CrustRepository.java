package com.monolitospizza.repositories;

import com.monolitospizza.model.Crust;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Matt Stine
 */
public interface CrustRepository extends PagingAndSortingRepository<Crust, Long> {
}
