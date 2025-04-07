package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role='job_seeker' or role='employer'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 5)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String password;

    @NotNull
    @Min(22)
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty
    @Pattern(regexp = "^(job_seeker|employer)$")
    @Column(columnDefinition = "varchar(15) not null")
    private String role;

}
