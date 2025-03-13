package com.example.lab07lms.Service;

import com.example.lab07lms.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean addStudent(Student student) {
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                return false;
            }
        }
        students.add(student);
        return true;
    }

    public boolean updateStudent(String id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                students.remove(s);
                return true;
            }
        }
        return false;
    }

    public Student searchById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Student> searchByClass(String className) {
        ArrayList<Student> studentsByClass = new ArrayList<>();
        for (Student s : students) {
            if (s.getClassName().equals(className)) {
                studentsByClass.add(s);
            }
        }
        return studentsByClass;
    }

    public ArrayList<Student> getPresentStudents() {
        ArrayList<Student> presentStudents = new ArrayList<>();
        for (Student s : students) {
            if (s.isPresent()) {
                presentStudents.add(s);
            }
        }
        return presentStudents;
    }

    public ArrayList<Student> getStudentAboveAvg() {
        ArrayList<Student> studentsAboveAvg = new ArrayList<>();
        double sum = 0;
        double avg = 0;
        for (Student s : students) {
            sum += s.getGPA();
        }
        avg = sum / students.size();
        for (Student s : students) {
            if (s.getGPA() >= avg) {
                studentsAboveAvg.add(s);
            }
        }
        return studentsAboveAvg;
    }

}
