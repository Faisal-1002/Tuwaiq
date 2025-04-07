package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobApplication;
import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.JobApplicationRepository;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import com.example.jobseekingsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    public List<JobApplication> getAllJobApplications(){
        return jobApplicationRepository.findAll();
    }

    public Boolean addJobApplication(JobApplication jobApplication){
        Integer user_id = null;
        Integer job_post_id = null;
        for (User user: userRepository.findAll()){
            if (jobApplication.getUser_id() == user.getId()){
                user_id = user.getId();
                break;
            }
        }
        for (JobPost jobPost: jobPostRepository.findAll()){
            if (jobApplication.getJob_post_id() == jobPost.getId()){
                job_post_id = jobPost.getId();
                break;
            }
        }
        for (JobApplication jobApplication1: jobApplicationRepository.findAll()){
            if (jobApplication1.getUser_id() == user_id && jobApplication1.getJob_post_id() == job_post_id)
                return false;
        }
        if (user_id == null || job_post_id == null){
            return false;
        }
        jobApplicationRepository.save(jobApplication);
        return true;
    }

    public Boolean deleteJobApplication(Integer id){
        JobApplication oldJobApplication = jobApplicationRepository.getById(id);
        if (oldJobApplication == null)
            return false;
        jobApplicationRepository.delete(oldJobApplication);
        return true;
    }

}
