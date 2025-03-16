package com.example.exam02.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotNull
    private int age;
    @NotNull
    @Positive
    private double balance;
    @NotEmpty
    @Pattern(regexp = "^(customer|librarian)$")
    private String role;

}
