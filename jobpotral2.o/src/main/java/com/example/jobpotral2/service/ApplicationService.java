package com.example.jobpotral2.service;

import java.util.List;

import com.example.jobpotral2.model.Application;

public interface ApplicationService {
    Application applyForJob(Long JobId, Long UserId);

    List<Application> getApplicants(Long JobId);

    List<Application> getAppliedJobs(Long UserId);

}
