package com.example.jparelation1exercise.Service;

import com.example.jparelation1exercise.Model.Student;
import com.example.jparelation1exercise.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer id, Student updatedStudent) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return;
        }
        student.setName(updatedStudent.getName());
        student.setAge(updatedStudent.getAge());
        student.setMajor(updatedStudent.getMajor());
        studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return;
        }
        studentRepository.delete(student);
    }

    public void changeStudentMajor(Integer studentId, String newMajor) {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            return;
        }
        student.setMajor(newMajor);
        student.getCourses().clear();
        studentRepository.save(student);
    }

    public List<Student> getStudentsByCourseId(Integer courseId) {
        return studentRepository.findStudentsByCourseId(courseId);
    }

}
