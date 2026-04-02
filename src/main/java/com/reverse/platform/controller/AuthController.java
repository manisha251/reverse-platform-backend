package com.reverse.platform.controller;

import com.reverse.platform.dto.CompanyRegisterRequest;
import com.reverse.platform.dto.ErrorResponse;
import com.reverse.platform.dto.LoginRequest;
import com.reverse.platform.dto.LoginResponse;
import com.reverse.platform.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = authService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Login failed: " + e.getMessage()));
        }
    }

    @PostMapping("/register-company")
    public ResponseEntity<?> registerCompany(@RequestBody CompanyRegisterRequest request) {
        try {
            // Validation
            if (request.getCompanyName() == null || request.getCompanyName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Company name is required"));
            }
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Email is required"));
            }
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Password is required"));
            }

            LoginResponse response = authService.registerCompany(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Registration failed: " + e.getMessage()));
        }
    }
}
