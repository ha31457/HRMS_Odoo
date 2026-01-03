package com.hss.hrms.repository;

import com.hss.hrms.enums.EmployeeStatus;
import com.hss.hrms.model.EmployeeJobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EmployeeJobDetailsRepository extends JpaRepository<EmployeeJobDetails, Integer> {
    Optional<EmployeeJobDetails> findByEmployeeId(Integer employeeId);
    List<EmployeeJobDetails> findByDepartmentId(Integer departmentId);
    List<EmployeeJobDetails> findByDesignationId(Integer designationId);
    List<EmployeeJobDetails> findByReportingManagerId(Integer reportingManagerId);
    List<EmployeeJobDetails> findByEmployeeStatus(EmployeeStatus status);
}