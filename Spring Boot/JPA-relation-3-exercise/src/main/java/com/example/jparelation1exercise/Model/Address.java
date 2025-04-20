package com.example.jparelation1exercise.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    private Integer id;
    @Column(columnDefinition = "varchar(20) not null")
    private String area;
    @Column(columnDefinition = "varchar(20) not null")
    private String street;
    @Column(columnDefinition = "varchar(20) not null")
    private String building_number;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;

}
