package com.akalya.inventorysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akalya.inventorysystem.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}