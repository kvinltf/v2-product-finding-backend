package com.kvinltf.productionfindingbackend.product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing {@link Product} entities
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}