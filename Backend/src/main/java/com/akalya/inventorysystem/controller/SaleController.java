package com.akalya.inventorysystem.controller;

import com.akalya.inventorysystem.model.Sale;
import com.akalya.inventorysystem.service.SaleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/record/{productId}")
    public Sale recordSale(@PathVariable Long productId, @RequestParam int quantitySold) {
        return saleService.recordSale(productId, quantitySold);
    }

    @GetMapping("/all")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }
}
