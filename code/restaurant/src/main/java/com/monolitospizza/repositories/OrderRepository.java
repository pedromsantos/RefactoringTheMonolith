package com.monolitospizza.repositories;

import com.monolitospizza.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Matt Stine
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
