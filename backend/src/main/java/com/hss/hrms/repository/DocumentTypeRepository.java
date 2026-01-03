package com.hss.hrms.repository;

import com.hss.hrms.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
    Optional<DocumentType> findByTypeName(String typeName);
    List<DocumentType> findByIsMandatory(Boolean isMandatory);
}