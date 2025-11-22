package com.akalya.inventorysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akalya.inventorysystem.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
