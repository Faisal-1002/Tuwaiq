package com.example.capstone01repo.Controller;

import com.example.capstone01repo.Api.ApiResponse;
import com.example.capstone01repo.Model.MerchantStock;
import com.example.capstone01repo.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStocks() {
        return ResponseEntity.status(200).body(merchantStockService.getAllMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        if (Boolean.TRUE.equals(merchantStockService.addMerchantStock(merchantStock))) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant stock added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Error adding merchant stock"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        if (Boolean.TRUE.equals(merchantStockService.updateMerchantStock(id, merchantStock))) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant stock updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant stock update failed"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id) {
        if (Boolean.TRUE.equals(merchantStockService.deleteMerchantStock(id))) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant stock deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant stock delete failed"));
    }

    @PutMapping("/addstock/{productId}/{merchantId}/{quantity}")
    public ResponseEntity addProductStock(@PathVariable String productId, @PathVariable String merchantId, @PathVariable Integer quantity) {
        if (Boolean.TRUE.equals(merchantStockService.addProductStock(productId, merchantId, quantity))) {
            return ResponseEntity.status(200).body(new ApiResponse("Product stock added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product stock could not be added"));
    }
}
