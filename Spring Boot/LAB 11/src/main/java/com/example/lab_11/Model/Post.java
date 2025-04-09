package com.example.lab_11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer category_id;

    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @NotEmpty
    @Column(columnDefinition = "varchar(50) not null")
    private String content;

    @Column(columnDefinition = "date")
    private LocalDate publish_date;

}
