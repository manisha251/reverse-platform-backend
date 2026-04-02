package com.reverse.platform.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private Boolean isVerifiedCompany;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private String status; // PENDING, ACCEPTED, DECLINED

    public Offer() {
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Offer(Long companyId, Long candidateId, String message, Boolean isVerifiedCompany, String companyName) {
        this.companyId = companyId;
        this.candidateId = candidateId;
        this.message = message;
        this.isVerifiedCompany = isVerifiedCompany;
        this.companyName = companyName;
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsVerifiedCompany() {
        return isVerifiedCompany;
    }

    public void setIsVerifiedCompany(Boolean isVerifiedCompany) {
        this.isVerifiedCompany = isVerifiedCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
