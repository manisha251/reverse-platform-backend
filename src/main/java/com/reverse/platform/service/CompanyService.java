package com.reverse.platform.service;

import com.reverse.platform.entity.Company;
import com.reverse.platform.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company registerCompany(Company company) {
        // Set default values for new company registration
        if (company.getIsVerified() == null) {
            company.setIsVerified(false);
        }
        if (company.getVerificationCode() == null || company.getVerificationCode().isEmpty()) {
            company.setVerificationCode(generateVerificationCode());
        }
        return companyRepository.save(company);
    }

    private String generateVerificationCode() {
        // Generate a random 6-digit verification code
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Optional<Company> findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    public Company verifyCompany(Long companyId, String verificationCode) {
        Optional<Company> companyOpt = companyRepository.findById(companyId);
        if (companyOpt.isPresent()) {
            Company company = companyOpt.get();
            if (company.getVerificationCode().equals(verificationCode)) {
                company.setIsVerified(true);
                return companyRepository.save(company);
            }
        }
        return null;
    }
}
