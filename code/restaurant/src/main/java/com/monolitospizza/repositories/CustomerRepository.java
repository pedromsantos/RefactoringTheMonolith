package com.monolitospizza.repositories;

import com.monolitospizza.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Matt Stine
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Customer findByEmail(String email);
}
