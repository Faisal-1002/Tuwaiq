package com.example.capstone02.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @NotEmpty(message = "Phone number cannot be empty")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String phone_number;

    @NotEmpty(message = "Role cannot be empty")
    @Pattern(regexp = "user|host", message = "Role must be either 'user' or 'host'")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

    @Pattern(regexp = "^(normal|premium)$", message = "Membership must be either 'normal' or 'premium'")
    @Column(columnDefinition = "varchar(10) not null default 'normal'")
    private String membership = "normal";

    @Column(columnDefinition = "date")
    private LocalDate subscription_date;

}
