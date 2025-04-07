package com.example.capstone01repo.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty(message = "Product ID must not be empty")
    private String productId;

    @Column(nullable = false)
    @NotEmpty(message = "Merchant ID must not be empty")
    private String merchantId;

    @Column(nullable = false)
    @NotNull(message = "Stock must not be empty")
    @Min(value = 11, message = "Stock must be more than 10 at the start")
    private Integer stock;
}