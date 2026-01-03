package com.hss.hrms.repository;

import com.hss.hrms.model.EmployeeLeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EmployeeLeaveBalanceRepository extends JpaRepository<EmployeeLeaveBalance, Integer> {
    List<EmployeeLeaveBalance> findByEmployeeId(Integer employeeId);
    List<EmployeeLeaveBalance> findByEmployeeIdAndYear(Integer employeeId, Integer year);
    Optional<EmployeeLeaveBalance> findByEmployeeIdAndLeaveTypeIdAndYear(Integer employeeId, Integer leaveTypeId, Integer year);
    List<EmployeeLeaveBalance> findByYear(Integer year);
}
