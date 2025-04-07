package com.example.capstone01repo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 4, message = "Name length must be more than 3")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Price must not be empty")
    @Positive(message = "Price must be positive")
    private Double price;

    @Column(nullable = false)
    @NotEmpty(message = "Category ID must not be empty")
    private String categoryId;

    @Column(length = 20)
    @Pattern(regexp = "^(Purchased|Refunded)$")
    private String status;

    @Column(nullable = false)
    @Min(value = 0, message = "Rating cannot be less than zero")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Double rating = 0.0;

    @Column(length = 1000)
    private String reviews;

    private LocalDateTime purchaseDateTime;
}