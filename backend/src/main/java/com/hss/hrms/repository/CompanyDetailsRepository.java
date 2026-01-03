package com.hss.hrms.repository;

import com.hss.hrms.model.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Integer> {
    Optional<CompanyDetails> findByIsActiveTrue();
    List<CompanyDetails> findByIsActive(Boolean isActive);
    Optional<CompanyDetails> findByCompanyName(String companyName);
}