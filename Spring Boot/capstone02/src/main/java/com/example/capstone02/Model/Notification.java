package com.example.capstone02.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    @NotNull(message = "Event ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer event_id;

    @NotEmpty(message = "Message cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String message;

    @Column(columnDefinition = "date")
    private LocalDate sent_at;

    @NotNull(message = "Read status must be set")
    @Column(columnDefinition = "boolean not null")
    private Boolean is_read;
}
