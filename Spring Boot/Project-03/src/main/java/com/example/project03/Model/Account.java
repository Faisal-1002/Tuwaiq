package com.example.project03.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$")
    @Column(columnDefinition = "varchar(19) not null unique")
    private String account_number;

    @NotEmpty
    @Positive
    @Column(columnDefinition = "double not null")
    private double balance;

    @AssertFalse
    @Column(columnDefinition = "boolean")
    private Boolean is_active;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
