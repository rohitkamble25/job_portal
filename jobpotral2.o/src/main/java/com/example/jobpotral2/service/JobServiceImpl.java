package com.example.jobpotral2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobpotral2.model.Job;
import com.example.jobpotral2.model.User;
import com.example.jobpotral2.repo.JobRepo;
import com.example.jobpotral2.repo.UserRepo;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Job postJob(Job job, Long recruiterId) {
        User recruiter = userRepo.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        job.setRecruiter(recruiter);
        return jobRepo.save(job);
    }

    @Override
    public List<Job> getJobsByRecruiter(Long recruiterId) {
        return jobRepo.findByRecruiterId(recruiterId);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }
}
