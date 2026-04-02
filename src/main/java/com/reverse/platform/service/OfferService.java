package com.reverse.platform.service;

import com.reverse.platform.entity.Offer;
import com.reverse.platform.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public Offer createOffer(Offer offer) {
        // Set creation timestamp if not set
        if (offer.getCreatedAt() == null) {
            offer.setCreatedAt(java.time.LocalDateTime.now());
        }
        // Set default status if not set
        if (offer.getStatus() == null || offer.getStatus().isEmpty()) {
            offer.setStatus("PENDING");
        }
        return offerRepository.save(offer);
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public List<Offer> getOffersByCandidateId(Long candidateId) {
        return offerRepository.findByCandidateId(candidateId);
    }

    public List<Offer> getOffersByCompanyId(Long companyId) {
        return offerRepository.findByCompanyId(companyId);
    }

    public Optional<Offer> getOfferById(Long id) {
        return offerRepository.findById(id);
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    public Offer updateOffer(Long id, Offer offer) {
        if (offerRepository.existsById(id)) {
            offer.setId(id);
            return offerRepository.save(offer);
        }
        return null;
    }

    public Offer updateOfferStatus(Long id, String status) {
        Optional<Offer> offerOpt = offerRepository.findById(id);
        if (offerOpt.isPresent()) {
            Offer offer = offerOpt.get();
            offer.setStatus(status);
            return offerRepository.save(offer);
        }
        throw new RuntimeException("Offer not found with id: " + id);
    }
}
