package com.hss.hrms.repository;

import com.hss.hrms.enums.LeaveStatus;
import com.hss.hrms.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findByEmployeeId(Integer employeeId);
    List<LeaveRequest> findByLeaveStatus(LeaveStatus status);
    List<LeaveRequest> findByEmployeeIdAndLeaveStatus(Integer employeeId, LeaveStatus status);
    List<LeaveRequest> findByReviewedBy(Integer reviewedBy);

    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employee.id IN (SELECT ejd.employee.id FROM EmployeeJobDetails ejd WHERE ejd.reportingManager.id = ?1) AND lr.leaveStatus = 'PENDING'")
    List<LeaveRequest> findPendingRequestsByManagerId(Integer managerId);

    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employee.id = ?1 AND ((lr.startDate BETWEEN ?2 AND ?3) OR (lr.endDate BETWEEN ?2 AND ?3))")
    List<LeaveRequest> findOverlappingLeaves(Integer employeeId, LocalDate startDate, LocalDate endDate);
}
