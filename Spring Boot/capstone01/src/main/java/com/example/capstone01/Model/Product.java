package com.example.capstone01.Model;

import com.example.capstone01.Service.CategoryService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Product {
    private final Category category;

    @NotEmpty(message = "ID must not me empty")
    private String id;
    @NotEmpty(message = "Name must be not empty")
    @Size(min = 4, message = "Name length must more than 3")
    private String name;
    @NotNull(message = "Price must not be empty")
    @Positive(message = "Price must be positive")
    private double price;
    @NotEmpty(message = "Category ID must not be empty")
    private String categoryId = category.getId();
}
