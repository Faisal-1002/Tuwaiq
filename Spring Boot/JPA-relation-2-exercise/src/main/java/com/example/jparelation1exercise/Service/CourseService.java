package com.example.jparelation1exercise.Service;

import com.example.jparelation1exercise.Api.ApiException;
import com.example.jparelation1exercise.Model.Course;
import com.example.jparelation1exercise.Model.Teacher;
import com.example.jparelation1exercise.Repository.CourseRepository;
import com.example.jparelation1exercise.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void addCourseWithTeacher(Integer teacherId, Course course){
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null)
            throw new ApiException("teacher is not found");
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void assignCourse(Integer courseId, Integer teacherId){
        Course course = courseRepository.findCourseById(courseId);
        Teacher teacher = teacherRepository.findTeacherById(teacherId);

        if (course == null || teacher == null)
            throw new ApiException("Course or teacher are not found");

        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void updateCourse(Integer id, Course course){
        Course courseToBeUpdated = courseRepository.findCourseById(id);
        if (courseToBeUpdated == null)
            throw new ApiException("Course not found");
        course.setId(courseToBeUpdated.getId());
        courseRepository.save(course);
    }

    public void deleteCourse(Integer id){
        Course course = courseRepository.findCourseById(id);
        if (course == null)
            throw new ApiException("Course not found");
        courseRepository.delete(course);
    }

    public String getTeacherName(Integer courseId){
        Course course = courseRepository.findCourseById(courseId);
        if (course == null)
            throw new ApiException("Course not found");
        return course.getTeacher().getName();
    }

}
