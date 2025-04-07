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
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty(message = "User ID must not be empty")
    private String userId;

    @Column(nullable = false)
    @NotEmpty(message = "Product ID must not be empty")
    private String productId;

    @Column(nullable = false)
    @NotNull(message = "Purchase date must not be null")
    private LocalDateTime purchaseDate;

    @Column(length = 20)
    private String status; // Purchased or Refunded
}