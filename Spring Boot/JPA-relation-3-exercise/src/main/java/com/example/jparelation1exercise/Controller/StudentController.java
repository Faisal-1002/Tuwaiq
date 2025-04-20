package com.example.jparelation1exercise.Controller;

import com.example.jparelation1exercise.Api.*;
import com.example.jparelation1exercise.Model.Student;
import com.example.jparelation1exercise.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
    }

    @PutMapping("/change-major/{studentId}/{newMajor}")
    public ResponseEntity changeStudentMajor(@PathVariable Integer studentId, @PathVariable String newMajor) {
        studentService.changeStudentMajor(studentId, newMajor);
        return ResponseEntity.status(200).body(new ApiResponse("Major changed and courses dropped successfully"));
    }

    @GetMapping("/students-by-course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourseId(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(studentService.getStudentsByCourseId(courseId));
    }

}
