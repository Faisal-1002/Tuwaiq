package com.example.lab06employeemanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Employee {
    @NotEmpty
    @Size(min = 3)
    private String id;
    @NotEmpty
    @Size(min = 5)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^05\\d{8}$")
    private String phoneNumber;
    @NotNull
    @Min(26)
    @Digits(integer = 2, fraction = 0)
    private int age;
    @NotEmpty
    @Pattern(regexp = "^(supervisor|coordinator)$")
    private String position;
    @NotNull
    @AssertFalse
    private boolean onLeave;
    @NotNull
    @PastOrPresent
    private LocalDate hireDate;
    @NotNull
    @Positive
    private int annualLeave;
}
