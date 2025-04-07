package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts(){
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

    public Boolean updateJobPost(Integer id, JobPost jobPost){
        JobPost oldJobPost = jobPostRepository.getById(id);
        if (oldJobPost == null)
            return false;
        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setLocation(jobPost.getLocation());
        oldJobPost.setSalary(jobPost.getSalary());
        jobPostRepository.save(oldJobPost);
        return true;
    }

    public Boolean deleteJobPost(Integer id){
        JobPost oldJobPost = jobPostRepository.getById(id);
        if (oldJobPost == null)
            return false;
        jobPostRepository.delete(oldJobPost);
        return true;
    }

}
