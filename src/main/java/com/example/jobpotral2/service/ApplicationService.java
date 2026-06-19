package com.example.jobpotral2.service;

import java.util.List;

import com.example.jobpotral2.dto.ApplicationRequest;
import com.example.jobpotral2.dto.ApplicationResponse;
import com.example.jobpotral2.enums.ApplicationStatus;

public interface ApplicationService {
    ApplicationResponse applyForJob(ApplicationRequest request, String applicantEmail);

    List<ApplicationResponse> getMyApplications(String applicantEmail);

    List<ApplicationResponse> getApplicationsForJob(Long jobId, String employerEmail);

    List<ApplicationResponse> getAllApplicationsForEmployer(String employerMail);

    ApplicationResponse updateApplicationStatus(Long applicationId, ApplicationStatus status,
            String employerNotes, String employerMail);

    void withdrawApplication(Long applicationId, String applicationEmail);
}
