package com.example.project03.DTO_In;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO_in {

    private Integer user_id;

    @NotEmpty
    @Size(min = 5, max = 10)
    private String username;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=!]).{6,}$")
    private String password;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String role;

    @NotEmpty
    private String position;

    @NotEmpty
    @Positive
    private Double salary;

}
