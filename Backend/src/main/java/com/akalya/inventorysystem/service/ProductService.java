package com.akalya.inventorysystem.service;

import com.akalya.inventorysystem.model.Product;
import com.akalya.inventorysystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // ➕ Add new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


    // 📋 Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 🔍 Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // ✏️ Update product
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            return productRepository.save(product);
        }).orElse(null);
    }

    // ❌ Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
