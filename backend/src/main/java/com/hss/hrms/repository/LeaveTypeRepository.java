package com.hss.hrms.repository;

import com.hss.hrms.enums.LeaveCategory;
import com.hss.hrms.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
    Optional<LeaveType> findByLeaveTypeName(String leaveTypeName);
    List<LeaveType> findByLeaveCategory(LeaveCategory category);
    List<LeaveType> findByIsActive(Boolean isActive);
}