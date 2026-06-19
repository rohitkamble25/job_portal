package com.example.jobpotral2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jobpotral2.entity.Job;
import com.example.jobpotral2.entity.JobApplication;
import com.example.jobpotral2.entity.User;
import com.example.jobpotral2.enums.ApplicationStatus;

@Repository
public interface ApplicationRepo extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByJobId(Long JobId);

    List<JobApplication> findByApplicant(User applicant);

    List<JobApplication> findByJob(Job job);

    List<JobApplication> findByJobAndApplicant(Job job, User applicant);

    boolean existsByJobAndApplicant(Job job, User applicant);

    List<JobApplication> findByApplicantAndStatus(User applicant, ApplicationStatus status);

    @Query("""
                SELECT ja FROM JobApplication ja
                WHERE ja.job.company.employer = :employer
            """)
    List<JobApplication> findAllByEmployer(@Param("employer") User employer);

    long countByApplicant(User applicant);

    long countByStatus(ApplicationStatus status);

}
