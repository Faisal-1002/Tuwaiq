package com.example.projecttrackersystemvalidation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty
    @Size(min = 3)
    private String id;
    @NotEmpty
    @Size(min = 9)
    private String title;
    @NotEmpty
    @Size(min = 16)
    private String description;
    @NotEmpty
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$")
    private String status;
    @NotEmpty
    @Size(min = 7)
    private String companyName;
}
