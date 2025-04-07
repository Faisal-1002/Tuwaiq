package com.example.capstone01repo.Controller;

import com.example.capstone01repo.Api.ApiResponse;
import com.example.capstone01repo.Model.Product;
import com.example.capstone01repo.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (Boolean.TRUE.equals(productService.addProduct(product))) {
            return ResponseEntity.status(200).body(new ApiResponse("Product added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product could not be added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (Boolean.TRUE.equals(productService.updateProduct(id, product))) {
            return ResponseEntity.status(200).body(new ApiResponse("Product updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product could not be updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        if (Boolean.TRUE.equals(productService.deleteProduct(id))) {
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product could not be deleted"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity searchProduct(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
        }
        return ResponseEntity.status(200).body(product);
    }

    @GetMapping("/getreviews/{id}")
    public ResponseEntity getAllReviews(@PathVariable Integer id) {
        if (productService.getReviews(id) == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Reviews could not be found"));
        }
        return ResponseEntity.status(200).body(productService.getReviews(id));
    }
}
