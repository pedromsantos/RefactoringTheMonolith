package com.monolitospizza.repositories;

import com.monolitospizza.model.Size;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Matt Stine
 */
public interface SizeRepository extends PagingAndSortingRepository<Size, Long> {
}
