package com.monolitospizza.repositories;

import com.monolitospizza.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Matt Stine
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
