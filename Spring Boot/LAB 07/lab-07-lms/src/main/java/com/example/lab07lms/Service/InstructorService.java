package com.example.lab07lms.Service;

import com.example.lab07lms.Model.Instructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {

    ArrayList<Instructor> instructors = new ArrayList<>();

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public boolean addInstructor(Instructor instructor) {
        for (Instructor in : instructors) {
            if (in.getId().equals(instructor.getId())) {
                return false;
            }
        }
        instructors.add(instructor);
        return true;
    }

    public boolean updateInstructor(String id, Instructor instructor) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId().equals(id)) {
                instructors.set(i, instructor);
                return true;
            }
        }
        return false;
    }

    public boolean deleteInstructor(String id) {
        for (Instructor instructor : instructors) {
            if (instructor.getId().equals(id)) {
                instructors.remove(instructor);
                return true;
            }
        }
        return false;
    }

    public Instructor searchInstructorById(String id) {
        for (Instructor instructor : instructors) {
            if (instructor.getId().equals(id)) {
                return instructor;
            }
        }
        return null;
    }

    public ArrayList<Instructor> searchInstructorByCourse(String course) {
        ArrayList<Instructor> instructorsByCourse = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.getCourse().equals(course)) {
                instructorsByCourse.add(instructor);
            }
        }
        return instructorsByCourse;
    }

    public ArrayList<Instructor> searchInstructorByDepartment(String department) {
        ArrayList<Instructor> instructorsByDepartment = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.getDepartment().equalsIgnoreCase(department)) {
                instructorsByDepartment.add(instructor);
            }
        }
        return instructorsByDepartment;
    }

    public List<List<Instructor>> getInstructorsByExperience() {
        ArrayList<Instructor> instructorsLessThanFiveYearsExperience = new ArrayList<>();
        ArrayList<Instructor> instructorsFiveYearsExperienceOrMore = new ArrayList<>();
        List<List<Instructor>> result = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.getExperience() < 5) {
                instructorsLessThanFiveYearsExperience.add(instructor);
            } else {
                instructorsFiveYearsExperienceOrMore.add(instructor);
            }
        }
        result.add(instructorsLessThanFiveYearsExperience);
        result.add(instructorsFiveYearsExperienceOrMore);
        return result;
    }

}
