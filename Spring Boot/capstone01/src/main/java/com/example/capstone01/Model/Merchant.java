package com.example.capstone01.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty(message = "ID must not be empty")
    private String id;
    @NotEmpty(message = "Name must be not empty")
    @Size(min = 4, message = "Name must be longer than 3")
    private String name;
}
