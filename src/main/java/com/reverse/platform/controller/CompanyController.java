package com.reverse.platform.controller;

import com.reverse.platform.dto.ErrorResponse;
import com.reverse.platform.entity.Company;
import com.reverse.platform.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register/company")
    public ResponseEntity<?> registerCompany(@RequestBody Company company) {
        try {
            Company registeredCompany = companyService.registerCompany(company);
            return ResponseEntity.ok(registeredCompany);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/dashboard/company/{id}")
    public ResponseEntity<Company> getCompanyDashboard(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/verified-companies")
    public ResponseEntity<List<Company>> getVerifiedCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        List<Company> verifiedCompanies = companies.stream()
                .filter(Company::getIsVerified)
                .toList();
        return ResponseEntity.ok(verifiedCompanies);
    }
}
