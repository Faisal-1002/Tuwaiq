package com.example.jparelation1exercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer age;
    @NotEmpty
    @Email
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;
    @NotEmpty
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher")
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> course;

}
