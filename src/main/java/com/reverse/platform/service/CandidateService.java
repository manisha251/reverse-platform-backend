package com.reverse.platform.service;

import com.reverse.platform.dto.CandidateResponse;
import com.reverse.platform.entity.Candidate;
import com.reverse.platform.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate registerCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public CandidateResponse getCandidateById(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        return candidate.map(this::convertToResponse).orElse(null);
    }

    public Optional<Candidate> findByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    private CandidateResponse convertToResponse(Candidate candidate) {
        return new CandidateResponse(
                candidate.getId(),
                candidate.getName(),
                candidate.getEmail(),
                candidate.getSkills()
        );
    }
}
