package com.example.capstone01.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @NotEmpty(message = "ID must not me empty")
    private String id;
    @NotEmpty(message = "Name must be not empty")
    @Size(min = 4, message = "Name length must more than 3")
    private String name;
    @NotNull(message = "Price must not be empty")
    @Positive(message = "Price must be positive")
    private double price;
    @NotEmpty(message = "Category ID must not be empty")
    private String categoryId;
    private LocalDateTime purchaseTime;
    @Min(value = 0, message = "Rating cannot be less than zero")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private double rating = 0.0;
    private ArrayList<String> reviews = new ArrayList<>();
}
