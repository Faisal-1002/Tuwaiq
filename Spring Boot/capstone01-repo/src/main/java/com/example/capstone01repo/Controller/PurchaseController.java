package com.example.capstone01repo.Controller;

import com.example.capstone01repo.Api.ApiResponse;
import com.example.capstone01repo.Model.Purchase;
import com.example.capstone01repo.Service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/get")
    public ResponseEntity getAllPurchases() {
        return ResponseEntity.status(200).body(purchaseService.getAllPurchases());
    }

    @PostMapping("/add")
    public ResponseEntity addPurchase(@RequestBody @Valid Purchase purchase, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        if (Boolean.TRUE.equals(purchaseService.addPurchase(purchase))) {
            return ResponseEntity.status(200).body(new ApiResponse("Purchase added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Purchase could not be added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePurchase(@PathVariable Integer id, @RequestBody @Valid Purchase purchase, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        if (Boolean.TRUE.equals(purchaseService.updatePurchase(id, purchase))) {
            return ResponseEntity.status(200).body(new ApiResponse("Purchase updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Purchase could not be updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePurchase(@PathVariable Integer id) {
        if (Boolean.TRUE.equals(purchaseService.deletePurchase(id))) {
            return ResponseEntity.status(200).body(new ApiResponse("Purchase deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Purchase could not be deleted"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity getPurchaseById(@PathVariable Integer id) {
        Purchase purchase = purchaseService.getPurchaseById(id);
        if (purchase == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Purchase not found"));
        }
        return ResponseEntity.status(200).body(purchase);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getPurchasesByUser(@PathVariable String userId) {
        return ResponseEntity.status(200).body(purchaseService.getPurchasesByUserId(userId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity getPurchasesByProduct(@PathVariable String productId) {
        return ResponseEntity.status(200).body(purchaseService.getPurchasesByProductId(productId));
    }
}