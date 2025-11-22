package com.akalya.inventorysystem.controller;

import com.akalya.inventorysystem.model.StockEntry;
import com.akalya.inventorysystem.service.StockEntryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "*")
public class StockEntryController {

    private final StockEntryService stockEntryService;

    public StockEntryController(StockEntryService stockEntryService) {
        this.stockEntryService = stockEntryService;
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<StockEntry> addStock(@PathVariable Long productId,
                                               @RequestParam int quantityAdded) {
        StockEntry entry = stockEntryService.addStockEntry(productId, quantityAdded);
        return ResponseEntity.ok(entry);
    }


    @GetMapping("/all")
    public List<StockEntry> getAllStockEntries() {
        return stockEntryService.getAllStockEntries();
    }
}
