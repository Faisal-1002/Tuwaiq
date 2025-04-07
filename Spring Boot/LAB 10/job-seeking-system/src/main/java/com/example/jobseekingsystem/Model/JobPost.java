package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "salary>=0")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 5)
    @Column(columnDefinition = "varchar(25) not null")
    private String title;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String description;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String location;

    @NotNull
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer salary;

    @NotNull
    @Column(columnDefinition = "date")
    private LocalDate posting_date;

}
