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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer user_id;

    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer post_id;

    @NotEmpty
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    @Column(columnDefinition = "date")
    private LocalDate comment_date;

}
