package com.example.capstone02.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Title cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @Pattern(regexp = "^(music|technology|sports|art)$", message = "Category must be one of: Music, Technology, Sports, Art")
    @Column(columnDefinition = "varchar(20) not null")
    private String category;

    @NotNull(message = "Date and time cannot be null")
    @Column(columnDefinition = "date not null")
    private LocalDate date_time;

    @NotNull(message = "User ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    @NotNull(message = "Place ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer place_id;

    @Column(columnDefinition = "int default 0")
    private Integer booking_count = 0;

    @NotNull(message = "Max capacity cannot be null")
    @Positive(message = "Max capacity must be positive")
    @Column(columnDefinition = "int not null")
    private Integer max_capacity;

    @NotNull(message = "Price must not be null")
    @PositiveOrZero(message = "Price must be zero or positive")
    @Column(columnDefinition = "decimal(10,2) not null")
    private Double price;
}
