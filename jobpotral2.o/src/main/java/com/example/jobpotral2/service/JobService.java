package com.example.jobpotral2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jobpotral2.model.Job;

@Service
public interface JobService {
    Job postJob(Job job, Long recruiterId);

    List<Job> getJobsByRecruiter(Long recruiterId);

    List<Job> getAllJobs();
}
