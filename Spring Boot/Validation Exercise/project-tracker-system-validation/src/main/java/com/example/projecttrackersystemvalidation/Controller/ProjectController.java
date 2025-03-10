package com.example.projecttrackersystemvalidation.Controller;

import com.example.projecttrackersystemvalidation.Api.ApiResponse;
import com.example.projecttrackersystemvalidation.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();
    @GetMapping("/get")
    public ArrayList<Project> getProjects() {
        return projects;
    }
    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        projects.add(project);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Successfully added project"));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        projects.set(index, project);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Successfully updated project"));
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        if (index < 0 || index >= projects.size())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Index out of bounds"));
        projects.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Successfully deleted project"));
    }
    @PutMapping("/update/{index}/{status}")
    public ResponseEntity updateProjectStatus(@PathVariable int index, @PathVariable String status) {
        if (index < 0 || index >= projects.size())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Index out of bounds"));
        projects.get(index).setStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Successfully updated project status"));
    }
    @GetMapping("/get/{title}")
    public Object searchProjects(@PathVariable String title) {
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title))
                return project;
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Project not found"));
    }
    @GetMapping("/getcompanyprojects/{companyName}")
    public Object getCompanyProjects(@PathVariable String companyName) {
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equalsIgnoreCase(companyName)){
                companyProjects.add(project);
            }
        }
        if (!(companyProjects.isEmpty()))
            return companyProjects;
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Company has no projects"));
    }
}
