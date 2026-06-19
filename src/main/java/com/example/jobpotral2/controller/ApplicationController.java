package com.example.jobpotral2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobpotral2.dto.ApiResponse;
import com.example.jobpotral2.dto.ApplicationRequest;
import com.example.jobpotral2.dto.ApplicationResponse;
import com.example.jobpotral2.enums.ApplicationStatus;
import com.example.jobpotral2.service.ApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@Tag(name = "Applications", description = "Job application management APIs")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/apply")
    @Operation(summary = "Apply for job", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<ApplicationResponse>> applyforJob(@Valid @RequestBody ApplicationRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        ApplicationResponse application = applicationService.applyForJob(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Applied SuccessFully", application));
    }

    @GetMapping("/my")
    @Operation(summary = "Get my applications", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> getMyApplications(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("My applications",
                applicationService.getMyApplications(userDetails.getUsername())));
    }

    @DeleteMapping("/{id}/withdraw")
    @Operation(summary = "Withdraw an application", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<Void>> withdrawApplication(@PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        applicationService.withdrawApplication(id, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Application withdrawn"));
    }

    @GetMapping("/job/{jobId}")
    @Operation(summary = "Get all applications for a job", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> getApplicationsForJob(
            @PathVariable Long jobId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("Applications fetched",
                applicationService.getApplicationsForJob(jobId, userDetails.getUsername())));
    }

    @GetMapping("/employer/all")
    @Operation(summary = "Get all applications received by employer", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> getAllEmployerApplications(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("All applications",
                applicationService.getAllApplicationsForEmployer(userDetails.getUsername())));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update application status", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<ApplicationResponse>> updateStatus(@PathVariable Long id,
            @RequestParam ApplicationStatus status,
            @RequestParam(required = false) String notes,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("Status updated",
                applicationService.updateApplicationStatus(id, status, notes, userDetails.getUsername())));
    }

}
