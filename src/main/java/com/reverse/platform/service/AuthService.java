package com.reverse.platform.service;

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
}
