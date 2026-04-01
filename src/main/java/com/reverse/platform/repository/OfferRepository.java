package com.reverse.platform.repository;

import com.reverse.platform.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByCandidateId(Long candidateId);
    List<Offer> findByCompanyId(Long companyId);
}
