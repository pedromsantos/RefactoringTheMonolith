package com.monolitospizza.repositories;

import com.monolitospizza.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Matt Stine
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
}
