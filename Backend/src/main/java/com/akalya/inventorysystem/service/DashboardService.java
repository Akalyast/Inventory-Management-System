package com.akalya.inventorysystem.service;

import com.akalya.inventorysystem.dto.DashboardSummary;
import com.akalya.inventorysystem.model.Sale;
import com.akalya.inventorysystem.model.StockEntry;
import com.akalya.inventorysystem.repository.ProductRepository;
import com.akalya.inventorysystem.repository.SaleRepository;
import com.akalya.inventorysystem.repository.StockEntryRepository;
import com.akalya.inventorysystem.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StockEntryRepository stockEntryRepository;

    public DashboardSummary getDashboardSummary() {
        DashboardSummary summary = new DashboardSummary();

        summary.setTotalProducts(productRepository.count());
        summary.setTotalVendors(vendorRepository.count());
        summary.setTotalSalesCount(saleRepository.count());

        // Calculate total revenue
        double totalRevenue = saleRepository.findAll().stream()
                .mapToDouble(sale -> sale.getProduct().getPrice() * sale.getQuantitySold())
                .sum();
        summary.setTotalRevenue(totalRevenue);

        // Recent records (latest 5)
        List<StockEntry> recentStock = stockEntryRepository.findAll().stream()
                .sorted((a, b) -> b.getEntryDate().compareTo(a.getEntryDate()))
                .limit(5)
                .toList();

        List<Sale> recentSales = saleRepository.findAll().stream()
                .sorted((a, b) -> b.getSaleDate().compareTo(a.getSaleDate()))
                .limit(5)
                .toList();

        summary.setRecentStockEntries(recentStock);
        summary.setRecentSales(recentSales);

        return summary;
    }
}
