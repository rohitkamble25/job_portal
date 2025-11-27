package com.example.jobpotral2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobpotral2.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestParam Long jobId, @RequestParam Long userId) {
        return ResponseEntity.ok(applicationService.applyForJob(jobId, userId));
    }

    @GetMapping("/applicants")
    public ResponseEntity<?> getApplicants(@RequestParam Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicants(jobId));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAppliedJobs(@RequestParam Long userId) {
        return ResponseEntity.ok(applicationService.getAppliedJobs(userId));
    }
}
