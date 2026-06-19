package com.example.jobpotral2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobpotral2.dto.ApiResponse;
import com.example.jobpotral2.dto.CompanyRequest;
import com.example.jobpotral2.dto.CompanyResponse;
import com.example.jobpotral2.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@Tag(name = "companies", description = "Company management APIs")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    @Operation(summary = "Get all approved companies")
    public ResponseEntity<ApiResponse<List<CompanyResponse>>> getAllCompanies() {
        return ResponseEntity.ok(ApiResponse.success("Companies fetched", companyService.getAllCompanies()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get company by ID")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompany(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Company fetched",
                companyService.getCompanyById(id)));
    }

    @GetMapping("/my-companies")
    @Operation(summary = "Get my companies", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<List<CompanyResponse>>> getMyCompanies(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("My companies",
                companyService.getMyCompanies(userDetails.getUsername())));
    }

    @PostMapping
    @Operation(summary = "Register a company", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(
            @Valid @RequestBody CompanyRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        CompanyResponse company = companyService.createCompany(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Company registered. Pending admin approval.", company));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update company", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("Company updated",
                companyService.updateCompany(id, request, userDetails.getUsername())));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete company", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ApiResponse<Void>> deleteCompany(@PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        companyService.deleteCompany(id, userDetails.getUsername());

        return ResponseEntity.ok(ApiResponse.success("Company Deleted"));
    }

}
