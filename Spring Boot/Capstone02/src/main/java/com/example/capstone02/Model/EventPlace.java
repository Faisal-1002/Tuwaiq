package com.example.capstone02.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Place name cannot be empty")
    @Column(columnDefinition = "varchar(40) not null")
    private String name;

    @NotEmpty(message = "Location cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    @NotNull(message = "Capacity cannot be null")
    @Positive(message = "Capacity must be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer capacity;
}
