package com.hss.hrms.repository;

import com.hss.hrms.model.EmployeeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Integer> {
    List<EmployeeDocument> findByEmployeeId(Integer employeeId);
    List<EmployeeDocument> findByEmployeeIdAndDocumentTypeId(Integer employeeId, Integer documentTypeId);
    List<EmployeeDocument> findByDocumentTypeId(Integer documentTypeId);

    @Query("SELECT ed FROM EmployeeDocument ed WHERE ed.expiryDate IS NOT NULL AND ed.expiryDate <= ?1")
    List<EmployeeDocument> findExpiringDocuments(LocalDate date);
}