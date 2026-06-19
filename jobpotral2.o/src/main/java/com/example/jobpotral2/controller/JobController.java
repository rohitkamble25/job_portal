package com.example.jobpotral2.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.jobpotral2.dto.ApiResponse;
import com.example.jobpotral2.dto.JobRequest;
import com.example.jobpotral2.dto.JobResponse;
import com.example.jobpotral2.enums.JobType;
import com.example.jobpotral2.service.JobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
@Tag(name = "Jobs", description = "Job management Apis")
public class JobController {

    private final JobService jobService;

    @GetMapping
    @Operation(summary = "Get all active jobs with pagination")
    public ResponseEntity<ApiResponse<Page<JobResponse>>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));

        return ResponseEntity.ok(ApiResponse.success("Jobs fetched",
                jobService.getAllActiveJobs(pageable)));
    }

    @GetMapping("/search")
    @Operation(summary = "search jobs by keyword, location, type and salary")
    public ResponseEntity<ApiResponse<Page<JobResponse>>> searchJobs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) JobType jobType,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return ResponseEntity.ok(ApiResponse.success("search results",
                jobService.searchJobs(keyword, location, jobType, minSalary, pageable)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get jo by id")
    public ResponseEntity<ApiResponse<JobResponse>> getJob(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Job fetched", jobService.getJobById(id)));
    }

    @PostMapping
    @Operation(summary = "Post a new job", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<JobResponse>> createJob(@Valid @RequestBody JobRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        JobResponse job = jobService.createJob(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Job posted successfully", job));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a job", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<JobResponse>> updateJob(@PathVariable Long id,
            @Valid @RequestBody JobRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("Job updated",
                jobService.updateJob(id, request, userDetails.getUsername())));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a job", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<Void>> deleteJob(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        jobService.deleteJob(id, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Job deleted successfully"));
    }

    @PatchMapping("/{id}/toggle")
    @Operation(summary = "Toggle job active/inactive", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<Void>> toggleJob(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        jobService.toggleJobStatus(id, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Job status toggled"));
    }

    @GetMapping("/my-jobs")
    @Operation(summary = "Get all jobs posted by current employer", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<List<JobResponse>>> getMyJobs(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("My jobs fetched",
                jobService.getJobByEmployer(userDetails.getUsername())));
    }

}
