package com.example.lab_11.Controller;

import com.example.lab_11.Api.ApiResponse;
import com.example.lab_11.Model.Category;
import com.example.lab_11.Service.CategoryService;
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
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @Valid @RequestBody Category category, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (categoryService.updateCategory(id, category))
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        if (categoryService.deleteCategory(id))
            return ResponseEntity.status(200).body(new ApiResponse("deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not deleted"));
    }

}
