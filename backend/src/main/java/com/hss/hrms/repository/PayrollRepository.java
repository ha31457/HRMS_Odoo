package com.hss.hrms.repository;

import com.hss.hrms.enums.PaymentStatus;
import com.hss.hrms.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Integer> {
    List<Payroll> findByEmployeeId(Integer employeeId);
    Optional<Payroll> findByEmployeeIdAndPayPeriodYearAndPayPeriodMonth(Integer employeeId, Integer year, Integer month);
    List<Payroll> findByPayPeriodYearAndPayPeriodMonth(Integer year, Integer month);
    List<Payroll> findByPaymentStatus(PaymentStatus status);

    @Query("SELECT p FROM Payroll p WHERE p.employee.id = ?1 ORDER BY p.payPeriodYear DESC, p.payPeriodMonth DESC")
    List<Payroll> findByEmployeeIdOrderByPeriodDesc(Integer employeeId);
}
