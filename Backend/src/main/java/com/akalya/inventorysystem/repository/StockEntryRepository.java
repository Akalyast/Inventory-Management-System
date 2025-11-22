package com.akalya.inventorysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akalya.inventorysystem.model.StockEntry;

public interface StockEntryRepository extends JpaRepository<StockEntry, Long> {
}