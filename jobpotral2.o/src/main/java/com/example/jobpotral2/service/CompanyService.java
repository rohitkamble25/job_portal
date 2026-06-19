package com.example.jobpotral2.service;

import java.util.List;

import com.example.jobpotral2.dto.CompanyRequest;
import com.example.jobpotral2.dto.CompanyResponse;

public interface CompanyService {
    CompanyResponse createCompany(CompanyRequest request, String employerEmail);

    CompanyResponse updateCompany(Long companyId, CompanyRequest request, String employerEmail);

    void deleteCompany(Long companyId, String employerEmail);

    CompanyResponse getCompanyById(Long companyId);

    List<CompanyResponse> getAllCompanies();

    List<CompanyResponse> getMyCompanies(String employerEmail);

    CompanyResponse approveCompany(Long companyId);

}
