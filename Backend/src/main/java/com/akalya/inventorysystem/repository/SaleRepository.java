package com.akalya.inventorysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akalya.inventorysystem.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}