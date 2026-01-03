package com.hss.hrms.repository;

import com.hss.hrms.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Integer> {
    Optional<Designation> findByDesignationName(String designationName);
    List<Designation> findByIsActive(Boolean isActive);
    List<Designation> findByDesignationLevelOrderByDesignationLevel(Integer level);
}