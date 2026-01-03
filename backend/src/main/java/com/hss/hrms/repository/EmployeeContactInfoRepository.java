package com.hss.hrms.repository;

import com.hss.hrms.model.EmployeeContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeContactInfoRepository extends JpaRepository<EmployeeContactInfo, Integer> {
    Optional<EmployeeContactInfo> findByEmployeeId(Integer employeeId);
    Optional<EmployeeContactInfo> findByPhonePrimary(String phonePrimary);
    boolean existsByEmployeeId(Integer employeeId);
}