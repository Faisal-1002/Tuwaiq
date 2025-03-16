package com.example.capstone01.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "ID must not be empty")
    private String id;
    @NotEmpty(message = "Product ID must not be empty")
    private String productId;
    @NotEmpty(message = "Merchant ID must not be empty")
    private String merchantId;
    @NotNull(message = "Stock must be not empty")
    @Min(value = 11, message = "Stock must be more than 10 at the start")
    private int stock;
}
