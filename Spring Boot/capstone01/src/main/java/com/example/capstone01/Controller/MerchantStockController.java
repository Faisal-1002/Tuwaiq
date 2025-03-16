package com.example.capstone01.Controller;

import com.example.capstone01.Api.ApiResponse;
import com.example.capstone01.Model.MerchantStock;
import com.example.capstone01.Service.MerchantStockService;
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
    public ResponseEntity getAllMerchantStocks(){
        return ResponseEntity.status(200).body(merchantStockService.getAllMerchantStocks());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        if (merchantStockService.addMerchantStock(merchantStock))
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Error adding Merchant Stock"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        if (merchantStockService.updateMerchantStock(id, merchantStock))
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock update failed"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String id){
        if (merchantStockService.deleteMerchantStock(id))
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock delete failed"));
    }
    @PutMapping("/addstock/{productId}/{merchantId}/{quantity}")
    public ResponseEntity addProductStock(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int quantity){
        if (merchantStockService.addProductStock(productId, merchantId, quantity))
            return ResponseEntity.status(200).body(new ApiResponse("Product Stock added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Product Stock already exists"));
    }
}
