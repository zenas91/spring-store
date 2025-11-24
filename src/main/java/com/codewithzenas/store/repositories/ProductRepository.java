package com.codewithzenas.store.repositories;

import com.codewithzenas.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}