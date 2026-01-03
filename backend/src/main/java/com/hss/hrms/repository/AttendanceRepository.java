package com.hss.hrms.repository;

import com.hss.hrms.enums.AttendanceStatus;
import com.hss.hrms.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByEmployeeId(Integer employeeId);
    Optional<Attendance> findByEmployeeIdAndAttendanceDate(Integer employeeId, LocalDate date);
    List<Attendance> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);
    List<Attendance> findByEmployeeIdAndAttendanceDateBetween(Integer employeeId, LocalDate startDate, LocalDate endDate);
    List<Attendance> findByAttendanceStatus(AttendanceStatus status);

    @Query("SELECT a FROM Attendance a WHERE a.employee.id = ?1 AND MONTH(a.attendanceDate) = ?2 AND YEAR(a.attendanceDate) = ?3")
    List<Attendance> findByEmployeeIdAndMonth(Integer employeeId, Integer month, Integer year);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.employee.id = ?1 AND a.attendanceStatus = ?2 AND MONTH(a.attendanceDate) = ?3 AND YEAR(a.attendanceDate) = ?4")
    Long countByEmployeeIdAndStatusAndMonth(Integer employeeId, AttendanceStatus status, Integer month, Integer year);
}