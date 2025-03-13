package com.example.lab07lms.Controller;

import com.example.lab07lms.Api.ApiResponse;
import com.example.lab07lms.Model.Student;
import com.example.lab07lms.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        if (studentService.addStudent(student)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student added successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Student already exists"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        if (studentService.updateStudent(id, student)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Student not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        if (studentService.deleteStudent(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Student not found"));
    }
    @GetMapping("/searchbyid/{id}")
    public ResponseEntity searchStudentById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.searchById(id));
    }
    @GetMapping("/searchbyclass/{className}")
    public ResponseEntity searchStudentsByClass(@PathVariable String className) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.searchByClass(className));
    }
    @GetMapping("/getpresentstudent")
    public ResponseEntity getPresentStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getPresentStudents());
    }
    @GetMapping("/getstudentaboveavg")
    public ResponseEntity getStudentAboveAverage() {
        return ResponseEntity.status(200).body(studentService.getStudentAboveAvg());
    }

}
