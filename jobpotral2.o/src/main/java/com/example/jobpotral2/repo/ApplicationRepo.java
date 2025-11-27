package com.example.jobpotral2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobpotral2.model.Application;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {
    List<Application> findByJobId(Long JobId);

    List<Application> findByJobSeekerId(Long UserId);
}
