package com.akalya.inventorysystem.service;

import com.akalya.inventorysystem.model.Vendor;
import com.akalya.inventorysystem.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor updateVendor(Long id, Vendor updatedVendor) {
        return vendorRepository.findById(id).map(vendor -> {
            vendor.setVendorName(updatedVendor.getVendorName());
            vendor.setCompanyName(updatedVendor.getCompanyName());
            vendor.setContactNumber(updatedVendor.getContactNumber());
            vendor.setEmail(updatedVendor.getEmail());
            return vendorRepository.save(vendor);
        }).orElse(null);
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
