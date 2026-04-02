package com.reverse.platform.controller;

import com.reverse.platform.entity.Offer;
import com.reverse.platform.entity.Company;
import com.reverse.platform.service.OfferService;
import com.reverse.platform.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/offers")
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
        try {
            // Get company details to include verification status
            Company company = companyService.getCompanyById(offer.getCompanyId());
            if (company == null) {
                return ResponseEntity.badRequest().body("Company not found");
            }

            // Set company verification status and name in the offer
            offer.setIsVerifiedCompany(company.getIsVerified());
            offer.setCompanyName(company.getCompanyName());

            Offer createdOffer = offerService.createOffer(offer);
            return ResponseEntity.ok(createdOffer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create offer: " + e.getMessage());
        }
    }

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/offers/candidate/{candidateId}")
    public ResponseEntity<List<Offer>> getOffersByCandidateId(@PathVariable Long candidateId) {
        List<Offer> offers = offerService.getOffersByCandidateId(candidateId);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/offers/company/{companyId}")
    public ResponseEntity<List<Offer>> getOffersByCompanyId(@PathVariable Long companyId) {
        List<Offer> offers = offerService.getOffersByCompanyId(companyId);
        return ResponseEntity.ok(offers);
    }

    @PutMapping("/offers/{id}/status")
    public ResponseEntity<?> updateOfferStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Offer updatedOffer = offerService.updateOfferStatus(id, status);
            return ResponseEntity.ok(updatedOffer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update offer status: " + e.getMessage());
        }
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id) {
        try {
            offerService.deleteOffer(id);
            return ResponseEntity.ok("Offer deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete offer: " + e.getMessage());
        }
    }
}
