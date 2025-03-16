package com.example.capstone01.Controller;

import com.example.capstone01.Api.ApiResponse;
import com.example.capstone01.Model.Category;
import com.example.capstone01.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategories(){
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (categoryService.addCategory(category)){
            return ResponseEntity.status(200).body(new ApiResponse("Category added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category already exists"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (categoryService.updateCategory(id, category))
            return ResponseEntity.status(200).body(new ApiResponse("Category updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Category update failed"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        if (categoryService.deleteCategory(id))
            return ResponseEntity.status(200).body(new ApiResponse("Category deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Category delete failed"));
    }

}
