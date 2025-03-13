package com.example.lab07lms.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty
    @Size(min = 10, max = 10)
    private String id;
    @NotEmpty
    @Size(min = 3)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;
    @NotEmpty
    private String className;
    @NotEmpty
    @Email
    private String email;
    @NotNull
    private boolean isPresent;
    @NotNull
    @Min(value = 0)
    @Max(value = 5)
    private double GPA;

}
