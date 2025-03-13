package com.example.lab07lms.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instructor {
    @NotEmpty
    @Size(min = 10, max = 10)
    private String id;
    @NotEmpty
    @Size(min = 3)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;
    @NotNull
    @Positive
    private int experience;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String course;
    @NotEmpty
    private String department;

}
