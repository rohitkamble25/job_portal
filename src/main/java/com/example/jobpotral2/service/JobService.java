package com.example.jobpotral2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.jobpotral2.dto.JobRequest;
import com.example.jobpotral2.dto.JobResponse;
import com.example.jobpotral2.enums.JobType;

public interface JobService {
    JobResponse createJob(JobRequest request, String employerEmail);

    JobResponse updateJob(Long jobId, JobRequest request, String employerEmail);

    void deleteJob(Long jobId, String employerEmail);

    JobResponse getJobById(Long jobId);

    Page<JobResponse> getAllActiveJobs(Pageable pageable);

    Page<JobResponse> searchJobs(String keyword, String location, JobType jobType, Double minSalary, Pageable pageable);

    List<JobResponse> getJobByEmployer(String employerEmail);

    void toggleJobStatus(Long JobId, String emploerEmail);

}
