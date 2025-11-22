package com.akalya.inventorysystem.service;

import com.akalya.inventorysystem.model.Product;
import com.akalya.inventorysystem.model.Sale;
import com.akalya.inventorysystem.repository.ProductRepository;
import com.akalya.inventorysystem.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    public Sale recordSale(Long productId, int quantitySold) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product not found with ID: " + productId));

        if (product.getQuantity() < quantitySold) {
            throw new RuntimeException("Not enough stock for this sale");
        }

        product.setQuantity(product.getQuantity() - quantitySold);
        productRepository.save(product);

        Sale sale = new Sale();
        sale.setQuantitySold(quantitySold);
        sale.setSaleDate(LocalDateTime.now());
        sale.setProduct(product);

        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}
