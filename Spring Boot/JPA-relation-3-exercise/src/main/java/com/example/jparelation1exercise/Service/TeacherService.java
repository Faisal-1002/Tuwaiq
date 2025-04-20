package com.example.jparelation1exercise.Service;

import com.example.jparelation1exercise.Api.ApiException;
import com.example.jparelation1exercise.Model.Teacher;
import com.example.jparelation1exercise.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new ApiException("Teacher is null");
        }
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id, Teacher teacher) {
        Teacher teacherToUpdate = teacherRepository.findTeacherById(id);
        if (teacherToUpdate == null) {
            throw new ApiException("Teacher not found");
        }
        teacher.setId(id);
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Integer id) {
        Teacher teacherToDelete = teacherRepository.findTeacherById(id);
        if (teacherToDelete == null) {
            throw new ApiException("Teacher not found");
        }
        teacherRepository.delete(teacherToDelete);
    }

    public Teacher getTeacher(Integer id) {
        return teacherRepository.findTeacherById(id);
    }

}
