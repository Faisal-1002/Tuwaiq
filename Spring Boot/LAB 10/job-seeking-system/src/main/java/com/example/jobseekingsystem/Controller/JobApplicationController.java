package com.example.jobseekingsystem.Controller;

import com.example.jobseekingsystem.Api.ApiResponse;
import com.example.jobseekingsystem.Model.JobApplication;
import com.example.jobseekingsystem.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobapplication")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getAllJobApplication(){
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/add")
    public ResponseEntity addJobApplication(@Valid @RequestBody JobApplication jobApplication, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (jobApplicationService.addJobApplication(jobApplication))
            return ResponseEntity.status(200).body(new ApiResponse("added"));
        return ResponseEntity.status(400).body(new ApiResponse("not added"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobApplication(@PathVariable Integer id){
        if(jobApplicationService.deleteJobApplication(id))
            return ResponseEntity.status(200).body(new ApiResponse("deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not deleted"));
    }
}
