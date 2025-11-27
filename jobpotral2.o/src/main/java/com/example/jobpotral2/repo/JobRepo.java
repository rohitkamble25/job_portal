package com.example.jobpotral2.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobpotral2.model.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {
    List<Job> findByRecruiterId(Long recruiterId);
}
