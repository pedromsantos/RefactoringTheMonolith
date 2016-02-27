package com.monolitospizza.repositories;

import com.monolitospizza.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Matt Stine
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
