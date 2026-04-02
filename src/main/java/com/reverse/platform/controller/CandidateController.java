package com.reverse.platform.controller;

import com.reverse.platform.dto.CandidateResponse;
import com.reverse.platform.dto.ErrorResponse;
import com.reverse.platform.entity.Candidate;
import com.reverse.platform.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/register/candidate")
    public ResponseEntity<?> registerCandidate(@RequestBody Candidate candidate) {
        try {
            // Check if email already exists
            if (candidateService.findByEmail(candidate.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Email already exists: " + candidate.getEmail()));
            }

            Candidate registeredCandidate = candidateService.registerCandidate(candidate);
            // Return response without password
            CandidateResponse response = new CandidateResponse(
                    registeredCandidate.getId(),
                    registeredCandidate.getName(),
                    registeredCandidate.getEmail(),
                    registeredCandidate.getSkills()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Registration failed: " + e.getMessage()));
        }
    }

    @GetMapping("/candidates")
    public ResponseEntity<List<CandidateResponse>> getAllCandidates() {
        List<CandidateResponse> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/candidates/{id}")
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable Long id) {
        CandidateResponse candidate = candidateService.getCandidateById(id);
        if (candidate != null) {
            return ResponseEntity.ok(candidate);
        }
        return ResponseEntity.notFound().build();
    }
}
