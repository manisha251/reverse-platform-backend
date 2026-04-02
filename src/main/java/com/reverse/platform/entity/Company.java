package com.reverse.platform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String description;

    @Column(nullable = false)
    private Boolean isVerified;

    @Column(nullable = false)
    private String verificationCode;

    public Company() {
        this.isVerified = false;
        this.verificationCode = generateVerificationCode();
    }

    public Company(String companyName, String email, String password, String description) {
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.description = description;
        this.isVerified = false;
        this.verificationCode = generateVerificationCode();
    }

    private String generateVerificationCode() {
        // Generate a random 6-digit verification code
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
