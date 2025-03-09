package com.example.lab05projecttrackersystem.Controller;

import com.example.lab05projecttrackersystem.Api.ApiResponse;
import com.example.lab05projecttrackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse addProject(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse("Successfully added project");
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody Project project) {
        projects.set(index, project);
        return new ApiResponse("Successfully updated project");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        projects.remove(index);
        return new ApiResponse("Successfully deleted project");
    }
    @PutMapping("/update/{index}/{status}")
    public ApiResponse updateProjectStatus(@PathVariable int index, @PathVariable String status) {
        projects.get(index).setStatus(status);
        return new ApiResponse("Successfully updated project status");
    }
    @GetMapping("/get/{title}")
    public Object searchProjects(@PathVariable String title) {
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title))
                return project;
        }
        return new ApiResponse("Project not found");
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
            return new ApiResponse("Company has no projects");
    }
}
