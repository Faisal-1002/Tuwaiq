package com.example.capstone02.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Booking ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer booking_id;

    @NotNull(message = "User ID must not be null")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    @Column(columnDefinition = "date")
    private LocalDate issued_at;

    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;

    @Column(columnDefinition = "longtext")
    private String qr_image;

}
