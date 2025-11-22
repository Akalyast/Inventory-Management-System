package com.akalya.inventorysystem.service;

import com.akalya.inventorysystem.model.Product;
import com.akalya.inventorysystem.model.StockEntry;
import com.akalya.inventorysystem.repository.ProductRepository;
import com.akalya.inventorysystem.repository.StockEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockEntryService {

    private final StockEntryRepository stockEntryRepository;
    private final ProductRepository productRepository;

    public StockEntryService(StockEntryRepository stockEntryRepository, ProductRepository productRepository) {
        this.stockEntryRepository = stockEntryRepository;
        this.productRepository = productRepository;
    }

    public StockEntry addStockEntry(Long productId, int quantityAdded) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        product.setQuantity(product.getQuantity() + quantityAdded);
        productRepository.save(product);

        StockEntry entry = new StockEntry();
        entry.setQuantityAdded(quantityAdded);
        entry.setEntryDate(LocalDateTime.now());
        entry.setProduct(product);

        return stockEntryRepository.save(entry);
    }

    public List<StockEntry> getAllStockEntries() {
        return stockEntryRepository.findAll();
    }
}
