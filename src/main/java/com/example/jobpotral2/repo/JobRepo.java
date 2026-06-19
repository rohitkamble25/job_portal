package com.example.jobpotral2.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobpotral2.entity.Job;
import com.example.jobpotral2.entity.User;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

    Page<Job> findByIsActiveTrue(Pageable pageable);

    List<Job> findByPostedBy(User postedBy);

    Page<Job> findByIsActiveTrueAndTitleContainingIgnoreCase(
            String keyword,
            Pageable pageable);

    List<Job> findByCompanyId(Long companyId);

    long countByIsActiveTrue();
}