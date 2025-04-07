package com.example.capstone01repo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, unique = true)
    @NotEmpty(message = "Username must not be empty")
    @Size(min = 6, message = "Username must be more than 5 letters")
    private String username;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 7, message = "Password must be at least 7 characters")
    private String password;

    @Column(nullable = false, length = 150, unique = true)
    @Email(message = "Email format is wrong")
    private String email;

    @Column(nullable = false, length = 20)
    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either admin or customer")
    private String role;

    @Column(nullable = false)
    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance must be positive")
    private Double balance;
}
