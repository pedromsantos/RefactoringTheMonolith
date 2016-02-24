package com.monolitospizza.repositories;

import com.monolitospizza.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Matt Stine
 */
public interface StoreRepository extends PagingAndSortingRepository<Store, Long> {
}
