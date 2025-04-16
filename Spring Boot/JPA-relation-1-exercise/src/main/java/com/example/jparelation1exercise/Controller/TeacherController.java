package com.example.jparelation1exercise.Controller;

import com.example.jparelation1exercise.Api.ApiResponse;
import com.example.jparelation1exercise.Model.Teacher;
import com.example.jparelation1exercise.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity getTeachers() {
        return ResponseEntity.status(200).body(teacherService.getAllTeachers());
    }
    @PostMapping("/add")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added teacher"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable Integer id, @Valid @RequestBody Teacher teacher) {
        teacherService.updateTeacher(id, teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated teacher"));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted teacher"));
    }
    @GetMapping("get-by-id/{id}")
    public ResponseEntity getTeacherById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(teacherService.getTeacher(id));
    }
}
