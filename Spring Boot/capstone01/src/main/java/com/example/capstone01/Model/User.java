package com.example.capstone01.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "ID must be not empty")
    private String id;
    @NotEmpty(message = "Username must not be empty")
    @Size(min = 6, message = "Username must be more than 5 letters")
    private String username;
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 7, message = "Password must be at least 7 characters")
    private String password;
    @Email(message = "Email format is wrong")
    private String email;
    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "^(Admin|Customrt)$", message = "Role must be either admin or customer")
    private String role;
    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance must be positive")
    private double balance;
}
