package com.example.lab05student.Controller;

import com.example.lab05student.Api.ApiResponse;
import com.example.lab05student.Api.StudentApiResponse;
import com.example.lab05student.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    ArrayList<Student> students = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Student> getStudents() {
        return students;
    }
    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Successfully added student");
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student student) {
        students.set(index, student);
        return new ApiResponse("Successfully updated student");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        students.remove(index);
        return new ApiResponse("Successfully deleted student");
    }
    @GetMapping("/get/categorized")
    public List<StudentApiResponse> getStudentsCategorized() {
        ArrayList<Student> firstHonorStudent = new ArrayList<>();
        ArrayList<Student> secondHonorStudent = new ArrayList<>();
        ArrayList<Student> thirdHonorStudent = new ArrayList<>();
        List<StudentApiResponse> responses = new ArrayList<>();
        //GPAs are out of 4.0
        for (Student student : students) {
            if (student.getGPA()>=3.75&&student.getGPA()<=4.0)
                firstHonorStudent.add(student);
            if (student.getGPA()>=3.5&&student.getGPA()<3.75)
                secondHonorStudent.add(student);
            if (student.getGPA()>=3.0&&student.getGPA()<3.5)
                thirdHonorStudent.add(student);
        }
        responses.add(new StudentApiResponse("This is the first honor list", firstHonorStudent));
        responses.add(new StudentApiResponse("This is the second honor list", secondHonorStudent));
        responses.add(new StudentApiResponse("This is the third honor list", thirdHonorStudent));
        return responses;
    }
    @GetMapping("/get/greaterthanavg")
    public ArrayList<Student> displayGreaterThanAverage() {
        ArrayList<Student> greaterThanAvg = new ArrayList<>();
        double sum = 0;
        for (Student student : students) {
            sum += student.getGPA();
        }
        double average = sum / students.size();
        for (Student student : students) {
            if (student.getGPA() >= average) {
                greaterThanAvg.add(student);
            }
        }
        return greaterThanAvg;
    }
}
