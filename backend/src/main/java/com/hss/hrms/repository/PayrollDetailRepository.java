package com.hss.hrms.repository;

import com.hss.hrms.model.PayrollDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollDetailRepository extends JpaRepository<PayrollDetail, Integer> {
    List<PayrollDetail> findByPayrollId(Integer payrollId);
    List<PayrollDetail> findByComponentId(Integer componentId);
}
