package com.hss.hrms.repository;

import com.hss.hrms.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByDepartmentCode(String departmentCode);
    Optional<Department> findByDepartmentName(String departmentName);
    List<Department> findByIsActive(Boolean isActive);
    boolean existsByDepartmentCode(String departmentCode);
}