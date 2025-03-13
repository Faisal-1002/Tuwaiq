package com.example.lab07lms.Controller;

import com.example.lab07lms.Api.ApiResponse;
import com.example.lab07lms.Model.Instructor;
import com.example.lab07lms.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity getInstructors() {
        return ResponseEntity.status(200).body(instructorService.getInstructors());
    }
    @PostMapping("/add")
    public ResponseEntity addInstructor(@RequestBody @Valid Instructor instructor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (instructorService.addInstructor(instructor)) {
            return ResponseEntity.status(200).body(new ApiResponse("Successfully added instructor"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Error adding instructor"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateInstructor(@PathVariable String id, @RequestBody @Valid Instructor instructor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (instructorService.updateInstructor(id, instructor)) {
            return ResponseEntity.status(200).body(new ApiResponse("Successfully updated instructor"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Error updating instructor"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInstructor(@PathVariable String id) {
        if (instructorService.deleteInstructor(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted instructor"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Error deleting instructor"));
    }
    @GetMapping("/searchbyid/{id}")
    public ResponseEntity searchById(@PathVariable String id) {
        return ResponseEntity.status(200).body(instructorService.searchInstructorById(id));
    }
    @GetMapping("/searchbycourse/{course}")
    public ResponseEntity searchByCourse(@PathVariable String course) {
        return ResponseEntity.status(200).body(instructorService.searchInstructorByCourse(course));
    }
    @GetMapping("/searchbydepartment/{department}")
    public ResponseEntity searchByDepartment(@PathVariable String department) {
        return ResponseEntity.status(200).body(instructorService.searchInstructorByDepartment(department));
    }
    //This method return two arrays. One array of instructors less than 5 years experience and the other one is for 5 years experience or more
    @GetMapping("/getinstructorsbyexperience")
    public ResponseEntity getInstructorsByExperience() {
        return ResponseEntity.status(200).body(instructorService.getInstructorsByExperience());
    }
}
