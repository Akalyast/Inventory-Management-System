package com.akalya.inventorysystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_entries")
public class StockEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantityAdded;
    private LocalDateTime entryDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public StockEntry() {}

    public StockEntry(int quantityAdded, LocalDateTime entryDate, Product product) {
        this.quantityAdded = quantityAdded;
        this.entryDate = entryDate;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public int getQuantityAdded() {
        return quantityAdded;
    }

    public void setQuantityAdded(int quantityAdded) {
        this.quantityAdded = quantityAdded;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
