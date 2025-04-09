package com.example.lab_11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 3)
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty
    @Email
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @Column(columnDefinition = "date")
    private LocalDate registration_date;

}
