package com.example.lab05student.Api;

import com.example.lab05student.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class StudentApiResponse {

    private String message;
    private ArrayList<Student> students;

}
