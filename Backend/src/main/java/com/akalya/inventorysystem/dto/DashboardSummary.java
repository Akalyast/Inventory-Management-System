package com.akalya.inventorysystem.dto;

import java.util.List;
import com.akalya.inventorysystem.model.Sale;
import com.akalya.inventorysystem.model.StockEntry;

public class DashboardSummary {
    private long totalProducts;
    private long totalVendors;
    private long totalSalesCount;
    private double totalRevenue;
    private List<StockEntry> recentStockEntries;
    private List<Sale> recentSales;

    // Getters and Setters
    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalVendors() {
        return totalVendors;
    }

    public void setTotalVendors(long totalVendors) {
        this.totalVendors = totalVendors;
    }

    public long getTotalSalesCount() {
        return totalSalesCount;
    }

    public void setTotalSalesCount(long totalSalesCount) {
        this.totalSalesCount = totalSalesCount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<StockEntry> getRecentStockEntries() {
        return recentStockEntries;
    }

    public void setRecentStockEntries(List<StockEntry> recentStockEntries) {
        this.recentStockEntries = recentStockEntries;
    }

    public List<Sale> getRecentSales() {
        return recentSales;
    }

    public void setRecentSales(List<Sale> recentSales) {
        this.recentSales = recentSales;
    }
}
