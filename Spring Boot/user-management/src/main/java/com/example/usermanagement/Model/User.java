package com.example.usermanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "role='user' or role='admin'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 5)
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty
    @Size(min = 5)
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty
    @Email
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(user|admin)$")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @NotNull
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer age;

}
