package com.hss.hrms.repository;

import com.hss.hrms.model.EmployeeBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeBankDetailsRepository extends JpaRepository<EmployeeBankDetails, Integer> {
    Optional<EmployeeBankDetails> findByEmployeeId(Integer employeeId);
    Optional<EmployeeBankDetails> findByPanNumber(String panNumber);
    Optional<EmployeeBankDetails> findByUanNumber(String uanNumber);
    boolean existsByEmployeeId(Integer employeeId);
    boolean existsByPanNumber(String panNumber);
}