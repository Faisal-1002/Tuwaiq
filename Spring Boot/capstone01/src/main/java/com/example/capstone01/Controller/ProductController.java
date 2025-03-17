package com.example.capstone01.Controller;

import com.example.capstone01.Api.ApiResponse;
import com.example.capstone01.Model.Product;
import com.example.capstone01.Service.ProductService;
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
    public ResponseEntity getAllProducts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (productService.addProduct(product)){
            return ResponseEntity.status(200).body(new ApiResponse("Product added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (productService.updateProduct(id, product))
            return ResponseEntity.status(200).body(new ApiResponse("Product updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Product not updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        if (productService.deleteProduct(id))
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Product not deleted"));
    }
    @GetMapping("/search/{id}")
    public ResponseEntity searchProduct(@PathVariable String id){
        if (productService.getProductById(id) == null)
            return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
        return ResponseEntity.status(200).body(productService.getProductById(id));
    }
    @GetMapping("/getreviews/{id}")
    public ResponseEntity getAllReviews(@PathVariable String id){
        if (productService.getReviews(id) == null)
            return ResponseEntity.status(400).body(new ApiResponse("Reviews could not be found"));
        return ResponseEntity.status(200).body(productService.getReviews(id));
    }

}
