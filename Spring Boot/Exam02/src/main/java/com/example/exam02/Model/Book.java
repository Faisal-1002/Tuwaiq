package com.example.exam02.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotNull
    private int number_of_pages;
    @NotNull
    private double price;
    @NotEmpty
    @Pattern(regexp = "^(novel|academic)$")
    private String category;
    @NotNull
    private boolean isAvailable;

}
