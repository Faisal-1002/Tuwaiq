package com.example.jparelation1exercise.Controller;

import com.example.jparelation1exercise.Api.ApiResponse;
import com.example.jparelation1exercise.Model.Course;
import com.example.jparelation1exercise.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getAllCourses(){
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }
    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Course course){
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }
    @PostMapping("/add/{id}")
    public ResponseEntity addCourseWithTeacher(@PathVariable Integer id, @Valid @RequestBody Course course){
        courseService.addCourseWithTeacher(id, course);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }
    @PutMapping("/assign/course-id/{courseId}/teacher-id/{teacherId}")
    public ResponseEntity assignCourse(@PathVariable Integer courseId, @PathVariable Integer teacherId){
        courseService.assignCourse(courseId, teacherId);
        return ResponseEntity.status(200).body(new ApiResponse("assigned"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable Integer id, @Valid @RequestBody Course course){
        courseService.updateCourse(id, course);
        return ResponseEntity.status(200).body(new ApiResponse("updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("deleted"));
    }
    @GetMapping("/get-teacher-name-by-course-id/{id}")
    public ResponseEntity getTeacherName(@PathVariable Integer id){
        return ResponseEntity.status(200).body(courseService.getTeacherName(id));
    }
}
