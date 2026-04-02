package com.reverse.platform.service;

import com.reverse.platform.dto.CompanyRegisterRequest;
import com.reverse.platform.dto.LoginRequest;
import com.reverse.platform.dto.LoginResponse;
import com.reverse.platform.entity.Candidate;
import com.reverse.platform.entity.Company;
import com.reverse.platform.repository.CandidateRepository;
import com.reverse.platform.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<Candidate> candidateOpt = candidateRepository.findByEmail(email);
        if (candidateOpt.isPresent()) {
            Candidate candidate = candidateOpt.get();
            if (candidate.getPassword().equals(password)) {
                return new LoginResponse("candidate", candidate);
            }
        }

        Optional<Company> companyOpt = companyRepository.findByEmail(email);
        if (companyOpt.isPresent()) {
            Company company = companyOpt.get();
            if (company.getPassword().equals(password)) {
                return new LoginResponse("company", company);
            }
        }

        throw new RuntimeException("Invalid email or password");
    }

    public LoginResponse registerCompany(CompanyRegisterRequest request) {
        // Check if email already exists
        Optional<Company> existingCompany = companyRepository.findByEmail(request.getEmail());
        if (existingCompany.isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Optional<Candidate> existingCandidate = candidateRepository.findByEmail(request.getEmail());
        if (existingCandidate.isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Create new company
        Company company = new Company(
            request.getCompanyName(),
            request.getEmail(),
            request.getPassword(),
            request.getDescription()
        );

        // Save to database
        Company savedCompany = companyRepository.save(company);
        return new LoginResponse("company", savedCompany);
    }
}
