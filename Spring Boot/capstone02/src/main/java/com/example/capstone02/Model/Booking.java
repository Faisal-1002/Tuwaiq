package com.example.capstone02.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    @NotNull(message = "Event ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer event_id;

    @NotEmpty(message = "Status cannot be empty")
    @Pattern(regexp = "confirmed|cancelled", message = "Status must be 'confirmed', 'cancelled', or 'rescheduled'")
    @Column(columnDefinition = "varchar(20) not null")
    private String status;

    @Column(columnDefinition = "date not null")
    private LocalDate booking_time;

    @Column(columnDefinition = "decimal(10,2)")
    private Double total_price;

}
