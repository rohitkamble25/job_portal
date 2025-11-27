package com.example.jobpotral2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.jobpotral2.model.Job;
import com.example.jobpotral2.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/post/{recruiterId}")
    public Job postJob(@RequestBody Job job, @PathVariable Long recruiterId) {
        return jobService.postJob(job, recruiterId);
    }

    @GetMapping("/recruiter/{recruiterId}")
    public List<Job> getJobsByRecruiter(@PathVariable Long recruiterId) {
        return jobService.getJobsByRecruiter(recruiterId);
    }

    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }
}
