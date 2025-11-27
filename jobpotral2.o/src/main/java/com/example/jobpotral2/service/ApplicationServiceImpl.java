package com.example.jobpotral2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobpotral2.model.Application;
import com.example.jobpotral2.model.Job;
import com.example.jobpotral2.model.User;
import com.example.jobpotral2.repo.ApplicationRepo;
import com.example.jobpotral2.repo.JobRepo;
import com.example.jobpotral2.repo.UserRepo;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ApplicationRepo applicationRepo;

    @Override
    public Application applyForJob(Long JobId, Long UserId) {
        Job job = jobRepo.findById(JobId).orElse(null);
        User user = userRepo.findById(UserId).orElse(null);

        if (job == null) {
            throw new RuntimeException("job not found");
        } else if (user == null) {
            throw new RuntimeException("User not found");
        } else {
            Application application = new Application();

            application.setJob(job);
            application.setJobSeeker(user);
            return applicationRepo.save(application);
        }

    }

    @Override
    public List<Application> getApplicants(Long JobId) {
        return applicationRepo.findByJobId(JobId);
    }

    @Override
    public List<Application> getAppliedJobs(Long UserId) {
        return applicationRepo.findByJobSeekerId(UserId);
    }

}
