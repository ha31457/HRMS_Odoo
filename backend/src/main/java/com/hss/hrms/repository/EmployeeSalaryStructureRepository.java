package com.hss.hrms.repository;

import com.hss.hrms.model.EmployeeSalaryStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface EmployeeSalaryStructureRepository extends JpaRepository<EmployeeSalaryStructure, Integer> {
    List<EmployeeSalaryStructure> findByEmployeeId(Integer employeeId);
    Optional<EmployeeSalaryStructure> findByEmployeeIdAndIsCurrentTrue(Integer employeeId);

    @Query("SELECT ess FROM EmployeeSalaryStructure ess WHERE ess.employee.id = ?1 AND ess.effectiveFrom <= ?2 AND (ess.effectiveTo IS NULL OR ess.effectiveTo >= ?2)")
    Optional<EmployeeSalaryStructure> findActiveStructureForDate(Integer employeeId, LocalDate date);
}